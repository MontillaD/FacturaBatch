package com.demo.FacturaBatch.config;

import com.demo.FacturaBatch.batch.processor.FacturaItemProcessor;
import com.demo.FacturaBatch.batch.reader.FacturaItemReader;
import com.demo.FacturaBatch.batch.writer.FacturaItemWriter;
import com.demo.FacturaBatch.domain.Factura;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;


@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    private  FacturaItemReader reader;

    @Autowired
    private  FacturaItemProcessor processor;

    @Autowired
    private  FacturaItemWriter writer;

    public BatchConfig(JobRepository jobRepository ,PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job facturaJob() {
        return new JobBuilder("facturaJob", jobRepository)
                .start(facturaStep())
                .build();
    }

    @Bean
    public Step facturaStep() {
        // Creamos un TransactionAttribute con aislamiento READ_COMMITTED
        DefaultTransactionAttribute txAttribute = new DefaultTransactionAttribute();
        txAttribute.setIsolationLevel(TransactionAttribute.ISOLATION_READ_COMMITTED);

        return new StepBuilder("facturaStep", jobRepository)
                .<Factura, Factura>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .transactionAttribute(txAttribute)
                .build();
    }
}

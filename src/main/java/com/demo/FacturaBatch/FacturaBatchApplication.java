package com.demo.FacturaBatch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableBatchProcessing
public class FacturaBatchApplication implements CommandLineRunner {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job facturaJob;

	public static void main(String[] args) {
		SpringApplication.run(FacturaBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length == 0) {
			return;
		}
		String fecha = args[0].split("=")[1];
	}
}

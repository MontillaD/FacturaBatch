package com.demo.FacturaBatch.batch.reader;

import com.demo.FacturaBatch.domain.Factura;
import com.demo.FacturaBatch.repository.FacturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@StepScope
public class FacturaItemReader implements ItemReader<Factura> {

    private final FacturaRepository facturaRepository;
    private final List<Factura> facturas;
    private int nextFacturaIndex;

    public FacturaItemReader(FacturaRepository facturaRepository, @Value("#{jobParameters['fecha']}") String fechaParam) {
        this.facturaRepository = facturaRepository;

        LocalDate fecha = (fechaParam != null && !fechaParam.isBlank())
                ? LocalDate.parse(fechaParam)
                : LocalDate.now();

        this.facturas = facturaRepository.findFacturasPorFechaNoExtraidas(fecha);
        log.error(String.valueOf(facturas.size()));
        this.nextFacturaIndex = 0;
    }

    @Override
    public Factura read() {
        if (nextFacturaIndex < facturas.size()) {
            return facturas.get(nextFacturaIndex++);
        }
        return null;
    }
}

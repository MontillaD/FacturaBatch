package com.demo.FacturaBatch;

import com.demo.FacturaBatch.batch.processor.FacturaItemProcessor;
import com.demo.FacturaBatch.domain.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaItemProcessorTest {

    private FacturaItemProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new FacturaItemProcessor();
    }

    @Test
    void testProcessExtraccionPago() throws Exception {
        Factura factura = new Factura(1L, "PROV001", "FACT001", new BigDecimal("1234.56"), "EUR",
                LocalDate.of(2025, 6, 30), "PENDIENTE", 1, "ES4343434");
        factura.setExtraccionPago(0);
        Factura processedFactura = processor.process(factura);
        assertNotNull(processedFactura);
        assertEquals(1, processedFactura.getExtraccionPago());
        assertSame(factura, processedFactura);
    }
}

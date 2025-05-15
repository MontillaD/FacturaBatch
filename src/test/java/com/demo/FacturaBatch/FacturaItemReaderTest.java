package com.demo.FacturaBatch;

import com.demo.FacturaBatch.batch.reader.FacturaItemReader;
import com.demo.FacturaBatch.domain.Factura;
import com.demo.FacturaBatch.repository.FacturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ItemReader;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;

public class FacturaItemReaderTest {

    FacturaRepository facturaRepository;
    FacturaItemReader facturaItemReader;

    List<Factura> facturasMock;

    @BeforeEach
    void setUp() {
        facturaRepository = mock(FacturaRepository.class);
        facturasMock = Arrays.asList(
                new Factura(1L, "PROV001", "FACT001", new BigDecimal("1234.56"), "EUR",
                        LocalDate.of(2025, 6, 30), "PENDIENTE", 1, "ES4343434"),
                new Factura(2L, "PROV002", "FACT002", new BigDecimal("789.00"), "USD",
                        LocalDate.of(2025, 7, 15), "PAGADA", 0, "ES32232")
        );
    }

    @Test
    void testReadReturnsFacturasOneByOne() {
        when(facturaRepository.findFacturasPorFechaNoExtraidas(any(LocalDate.class)))
                .thenReturn(facturasMock);

        facturaItemReader = new FacturaItemReader(facturaRepository, "2025-05-15");

        Factura first = facturaItemReader.read();
        assertNotNull(first);
        assertEquals(1L, first.getId());

        Factura second = facturaItemReader.read();
        assertNotNull(second);
        assertEquals(2L, second.getId());

        Factura third = facturaItemReader.read();
        assertNull(third);
    }

}

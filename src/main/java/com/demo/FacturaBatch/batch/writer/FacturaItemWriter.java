package com.demo.FacturaBatch.batch.writer;

import com.demo.FacturaBatch.domain.Factura;
import com.demo.FacturaBatch.repository.FacturaRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Component
public class FacturaItemWriter implements ItemWriter<Factura> {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public void write(Chunk<? extends Factura> chunk) throws Exception {
        facturaRepository.saveAll(chunk);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("facturas_extraidas.csv"),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (Factura f : chunk) {
                writer.write(String.join(",",
                        f.getCodigoProveedor(),
                        f.getCodigoFactura(),
                        f.getImporte().toString(),
                        f.getDivisa(),
                        f.getFechaVencimiento().toString(),
                        f.getIban()));
                writer.newLine();
            }
        }
    }
}

package com.demo.FacturaBatch.batch.processor;

import com.demo.FacturaBatch.domain.Factura;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FacturaItemProcessor implements ItemProcessor<Factura, Factura> {
    @Override
    public Factura process(Factura factura) {
        factura.setExtraccionPago(1);
        return factura;
    }
}

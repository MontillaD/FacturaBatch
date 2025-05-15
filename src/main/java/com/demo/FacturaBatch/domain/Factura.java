package com.demo.FacturaBatch.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="FACTURAS")
@Data
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_de_proveedor")
    private String codigoProveedor;

    @Column(name = "codigo_de_factura")
    private String codigoFactura;

    @Column(name = "importe")
    private BigDecimal importe;

    @Column(name = "divisa")
    private String divisa;

    @Column(name = "fecha_de_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "estado")
    private String estado;

    @Column(name = "extraccion_pago")
    private int extraccionPago;

    @Column(name = "iban")
    private String iban;
}

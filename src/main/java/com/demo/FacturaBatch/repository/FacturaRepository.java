package com.demo.FacturaBatch.repository;

import com.demo.FacturaBatch.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

    @Query("SELECT f FROM Factura f WHERE f.fechaVencimiento = :fecha AND f.extraccionPago = 0")
    List<Factura> findFacturasPorFechaNoExtraidas(@Param("fecha") LocalDate fecha);
}

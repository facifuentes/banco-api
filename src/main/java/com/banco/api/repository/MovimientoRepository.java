package com.banco.api.repository;

import com.banco.api.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long>, JpaSpecificationExecutor<Movimiento> {

    Optional<Movimiento> findFirstByMvtCuenta_CntNumeroOrderByMvtFechaDesc(long numeroCuenta);
}
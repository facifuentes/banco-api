package com.banco.api.repository;

import com.banco.api.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long>, JpaSpecificationExecutor<Movimiento> {

    Optional<Movimiento> findFirstByMvtCuenta_CntNumeroOrderByMvtFechaDesc(long numeroCuenta);

    @Query(value = "SELECT mvt FROM Movimiento  mvt " +
            "WHERE mvt.mvtCuenta.cntCliente.cliPersona.identificacion = ?1 and " +
            " (mvt.mvtFecha>=?2 and mvt.mvtFecha<=?3) order by mvt.mvtFecha asc")
    List<Movimiento> findAllByClientAndFechas(String identificacion, LocalDateTime fechaInicial, LocalDateTime fechaFinal);
}
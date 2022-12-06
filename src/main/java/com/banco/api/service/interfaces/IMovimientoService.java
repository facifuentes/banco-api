package com.banco.api.service.interfaces;


import com.banco.api.model.Movimiento;

import java.time.LocalDateTime;
import java.util.List;

public interface IMovimientoService {
    Movimiento save(Movimiento movimiento);

    Movimiento findById(long idMovimiento);

    List<Movimiento> findByClienteAndFechas(String identificacion, LocalDateTime fechaInicial, LocalDateTime fechaFinal);

}

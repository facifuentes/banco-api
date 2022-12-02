package com.banco.api.service.interfaces;


import com.banco.api.model.Movimiento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public interface IMovimientoService {
    Movimiento save(Movimiento movimiento);

    Movimiento findById(@NotNull long idMovimiento);

    List<Movimiento> findByClienteAndFechas(@NotNull @NotBlank String identificacion, @NotNull @NotBlank LocalDateTime fechaInicial, @NotNull @NotBlank LocalDateTime fechaFinal);

}

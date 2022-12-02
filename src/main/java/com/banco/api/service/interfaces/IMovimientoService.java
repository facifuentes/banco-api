package com.banco.api.service.interfaces;


import com.banco.api.model.Movimiento;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IMovimientoService {
    Movimiento save(Movimiento movimiento);

    Movimiento findById(@NotNull long idMovimiento) throws Exception;

    List<Movimiento> findAll();
}

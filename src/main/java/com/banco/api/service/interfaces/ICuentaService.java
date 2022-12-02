package com.banco.api.service.interfaces;

import com.banco.api.model.Cuenta;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ICuentaService {
    Cuenta save(Cuenta cliente);

    Cuenta update(Cuenta cliente);

    void delete(@NotNull long idCliente);

    Cuenta findByNumeroCuenta(@NotNull long idCuenta);

    List<Cuenta> findAll();
}

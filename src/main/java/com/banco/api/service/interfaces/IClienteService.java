package com.banco.api.service.interfaces;

import com.banco.api.model.Cliente;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IClienteService {

    Cliente save(Cliente cliente);

    Cliente update(Cliente cliente);

    void delete(@NotNull long idCliente);

    Cliente findById(@NotNull long idCliente) throws Exception;

    List<Cliente> findAll();
}

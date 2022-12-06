package com.banco.api.service.interfaces;

import com.banco.api.model.Cliente;

import java.util.List;

public interface IClienteService {

    Cliente save(Cliente cliente);

    Cliente update(Cliente cliente);

    void delete(long idCliente);

    Cliente findById(long idCliente);

    List<Cliente> findAll();
}

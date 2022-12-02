package com.banco.api.service.impl;

import com.banco.api.exception.NotFoundException;
import com.banco.api.model.Cliente;
import com.banco.api.repository.ClienteRepository;
import com.banco.api.service.interfaces.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Scope("singleton")
@RequiredArgsConstructor
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Cliente save(@NotNull Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(@NotNull  long idCliente) {
        clienteRepository.deleteById(idCliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(@NotNull long idCliente)  {
        return clienteRepository.findById(idCliente).orElseThrow(()->new NotFoundException("Cliente no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}

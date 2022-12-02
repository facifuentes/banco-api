package com.banco.api.service.impl;


import com.banco.api.exception.NotFoundException;
import com.banco.api.model.Cuenta;
import com.banco.api.repository.CuentaRepository;
import com.banco.api.service.interfaces.ICuentaService;
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
public class CuentaService implements ICuentaService {

    private final CuentaRepository cuentaRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Cuenta update(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(long idCuenta) {
        cuentaRepository.deleteById(idCuenta);
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta findByNumeroCuenta(@NotNull  long numeroCuenta)  {
        return cuentaRepository.findCuentaByCntNumero(numeroCuenta)
                .orElseThrow(()->new NotFoundException("Numero de cuenta no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }
}

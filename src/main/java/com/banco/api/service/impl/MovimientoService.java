package com.banco.api.service.impl;


import com.banco.api.exception.ForbiddenException;
import com.banco.api.exception.NotFoundException;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Enum;
import com.banco.api.model.Movimiento;
import com.banco.api.repository.CuentaRepository;
import com.banco.api.repository.MovimientoRepository;
import com.banco.api.service.interfaces.IMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Scope("singleton")
@RequiredArgsConstructor
public class MovimientoService implements IMovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Movimiento save(Movimiento movimiento) {
        long numeroCuenta=movimiento.getMvtCuenta().getCntNumero();

        Cuenta cuenta = cuentaRepository.findCuentaByCntNumero(numeroCuenta).orElseThrow(()->new NotFoundException("Cuenta no encontrada"));
        BigDecimal saldo = getSaldoCuenta(numeroCuenta, cuenta.getCntSaldoInicial());

        BigDecimal valorMovimiento =getValorMovimiento(movimiento.getMvtTipo(), movimiento.getMvtValor());
        saldo= saldo.add(valorMovimiento);

        if(saldo.compareTo(BigDecimal.ZERO)<0){
            throw new ForbiddenException("Saldo no disponible");
        }

        movimiento.setMvtCuenta(cuenta);
        movimiento.setMvtValor(valorMovimiento);
        movimiento.setMvtSaldo(saldo);
        movimiento.setMvtFecha(LocalDateTime.now());

        return movimientoRepository.save(movimiento);
    }

    private BigDecimal getValorMovimiento(String tipoMovimiento, BigDecimal valor){
        if(tipoMovimiento.equals(Enum.EnumTipoMovimiento.RETIRO.toString())){
            valor= valor.negate();
        }
        return valor;
    }

    private BigDecimal getSaldoCuenta(long numeroCuenta, BigDecimal saldoActual){

        Optional<Movimiento> ultimoMovimiento=movimientoRepository.findFirstByMvtCuenta_CntNumeroOrderByMvtFechaDesc(numeroCuenta);

        if(ultimoMovimiento.isPresent()){
            saldoActual = ultimoMovimiento.get().getMvtSaldo();
        }

        return saldoActual;
    }


    @Override
    @Transactional(readOnly = true)
    public Movimiento findById(long idMovimiento)  {
        return movimientoRepository.findById(idMovimiento).orElseThrow(()->new NotFoundException("Movimiento no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movimiento> findByClienteAndFechas(String identificacion, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        return movimientoRepository.findAllByClientAndFechas(identificacion,fechaInicial,fechaFinal);
    }



}

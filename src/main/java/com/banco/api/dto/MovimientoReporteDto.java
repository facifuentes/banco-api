package com.banco.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MovimientoReporteDto {

    private String mvtFecha;

    private String cliente;

    private Long cuentaNumero;

    private Long mvtValorMovimiento;

    private Long mvtSaldo;

    private Long saldoInicial;

    private boolean cntEstado;

    private String mvtTipoCuenta;

}

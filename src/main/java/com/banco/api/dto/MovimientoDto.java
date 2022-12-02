package com.banco.api.dto;


import com.banco.api.model.Enum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MovimientoDto {

    private Long mvtId;

    @NotNull(message = "Tipo de movimiento obligatorio RETIRO/DEPOSITO")
    private Enum.EnumTipoMovimiento mvtTipo;

    @NotNull(message = "Valor obligatorio")
    private Long mvtValor;

    @NotNull(message = "Numero de la cuenta es obligatorio")
    private Long cuentaNumero;

    private String mvtFecha;

    private Long mvtSaldo;
}

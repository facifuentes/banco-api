package com.banco.api.dto;


import com.banco.api.model.Enum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CuentaDto {

    private Long cntId;

    @NotNull(message = "El numero es obligatorio")
    private Long cntNumero;

    @NotNull(message = "El tipo es obligatorio AHORROS/CORRIENTE")
    private Enum.EnumTipoCuenta cntTipo;

    @NotNull(message = "El saldo inicial es obligatorio")
    private BigDecimal cntSaldoInicial;

    @NotNull(message = "El estado es obligatorio")
    private Boolean cntEstado;

    @NotNull(message = "El cliente es obligatorio")
    private Long idClient;
}

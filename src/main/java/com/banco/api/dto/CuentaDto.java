package com.banco.api.dto;


import com.banco.api.model.Enum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CuentaDto {

    private Long cntId;

    private Long cntNumero;

    private Enum.EnumTipoCuenta cntTipo;

    private Long cntSaldoInicial;

    private Boolean cntEstado;

    private Long idClient;
}

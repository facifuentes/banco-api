package com.banco.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClienteDto {
    private Long cliId;

    private String cliClave;

    private Boolean cliEstado;

    private String identificacion;

    private String nombre;

    private String direccion;

    private String telefono;
}

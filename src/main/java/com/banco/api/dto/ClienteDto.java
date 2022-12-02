package com.banco.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClienteDto {
    private Long cliId;

    @NotNull(message = "La clave es obligatoria")
    private String cliClave;

    @NotNull(message = "El estado obligatorio")
    private Boolean cliEstado;

    @NotNull(message = "La identificacion obligatoria")
    private String identificacion;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "La direccion obligatoria")
    private String direccion;

    @NotNull(message = "El telefono es obligatorio")
    private String telefono;
}

package com.banco.api.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Persona  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "genero")
    private String genero;

    @Column(name = "edad")
    private Long edad;

    @Column(name = "identificacion", nullable = false)
    private String identificacion;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false)
    private String telefono;

}

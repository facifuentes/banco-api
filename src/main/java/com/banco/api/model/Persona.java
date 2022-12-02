package com.banco.api.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "persona")
public class Persona  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id", insertable = false, updatable = false)
    private Long perId;

    @Column(name = "per_nombre", nullable = false)
    private String perNombre;

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

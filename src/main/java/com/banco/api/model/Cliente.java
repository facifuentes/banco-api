package com.banco.api.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)//This means we can chain multiple set operations together in one statement
@Table(name = "cliente")
public class Cliente  extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id", insertable = false, updatable = false)
    private Long cliId;

    @Column(name = "cli_clave", nullable = false)
    private String cliClave;

    @Column(name = "cli_estado", nullable = false)
    private Boolean cliEstado;

}

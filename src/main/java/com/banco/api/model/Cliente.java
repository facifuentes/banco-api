package com.banco.api.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "cliente")
public class Cliente  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id", insertable = false, updatable = false)
    private Long cliId;

    @Column(name = "cli_clave", nullable = false)
    private String cliClave;

    @Column(name = "cli_estado", nullable = false)
    private Boolean cliEstado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cli_persona")
    @JsonManagedReference
    @NotNull(message = "La persona es obligatoria")
    private Persona cliPersona;

}

package com.banco.api.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "movimiento")
public class Movimiento  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mvtId;

    @Column(name = "mvt_fecha", insertable = false, updatable = false)
    private LocalDateTime mvtFecha;

    @Column(name = "mvt_tipo", nullable = false)
    private String mvtTipo;

    @Column(name = "mvt_valor", nullable = false)
    @NotNull(message = "El valor es obligatorio")
    private Long mvtValor;

    @Column(name = "mvt_saldo", nullable = false)
    private Long mvtSaldo;

    @ManyToOne
    @JoinColumn(name = "mvt_cuenta")
    @JsonManagedReference
    private Cuenta mvtCuenta;

}

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
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "movimiento")
public class Movimiento  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mvt_id", updatable = false)
    private Long mvtId;

    @Column(name = "mvt_fecha", insertable = false, updatable = false)
    private LocalDateTime mvtFecha;

    @Column(name = "mvt_tipo", nullable = false)
    private String mvtTipo;

    @Column(name = "mvt_valor", nullable = false)
    private Long mvtValor;

    @Column(name = "mvt_saldo", nullable = false)
    private Long mvtSaldo;

    @ManyToOne
    @JoinColumn(name = "mvt_cuenta")
    @JsonManagedReference
    @NotNull(message = "La cuenta es obligatoria")
    private Cuenta mvtCuenta;

}

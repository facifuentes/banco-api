package com.banco.api.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cuenta")
public class Cuenta  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cnt_id", updatable = false)
    private Long cntId;

    @Column(name = "cnt_numero", nullable = false)
    private Long cntNumero;

    @Column(name = "cnt_tipo", nullable = false)
    private String cntTipo;

    @Column(name = "cnt_saldo_inicial", nullable = false)
    private Long cntSaldoInicial;

    @Column(name = "cnt_estado", nullable = false)
    private Boolean cntEstado;

    @ManyToOne
    @JoinColumn(name = "cnt_cliente")
    @JsonManagedReference
    @NotNull(message = "El cliente es obligatorio")
    private Cliente cntCliente;

}

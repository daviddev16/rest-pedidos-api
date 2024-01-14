package com.daviddev16.pedido;

import com.daviddev16.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/* LOMBOK */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

/* JPA */
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @Column(name = "pedido_id")
    @SequenceGenerator(
            name = "pedido_id_seq",
            sequenceName = "pedido_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "pedido_id_seq",
            strategy = GenerationType.SEQUENCE
    )
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "cliente_id",
            foreignKey =
                @ForeignKey(name = "fk_pedido_cliente", value = ConstraintMode.CONSTRAINT)
    )
    private Cliente cliente;

    @Column(
            name = "data_pedido",
            nullable = false
    )
    private LocalDate dataPedido;

    @Column(
            name = "valor_unitario",
            nullable = false,
            precision = 20,
            scale = 2
    )
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status_pedido",
            nullable = false
    )
    private StatusPedido statusPedido;

    @OneToMany(
            mappedBy = "pedido",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private Set<ItemPedido> itens = new HashSet<>();

}

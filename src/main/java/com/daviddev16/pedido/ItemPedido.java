package com.daviddev16.pedido;

import com.daviddev16.produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/* LOMBOK */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

/* JPA */

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @Column(name = "item_id")
    @SequenceGenerator(
            name = "item_id_seq",
            sequenceName = "item_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "item_id_seq",
            strategy = GenerationType.SEQUENCE
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(
            name = "pedido_id",
            foreignKey =
                @ForeignKey(name = "fk_item_idpedido")
    )
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(
            name = "produto_id",
            foreignKey =
                @ForeignKey(name = "fk_item_idproduto")
    )
    private Produto produto;

    @Column(
            name = "quantidade",
            nullable = false
    )
    private Integer quantidade;


}

package com.daviddev16.produto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/* LOMBOK */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

/* JPA */
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produto_id")
    private Integer id;

    @NotBlank(message = "{msg.produto.descricao.obrigatorio}")
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "{msg.produto.valor-unitario.obrigatorio}")
    @Column(name = "valor_unitario", precision = 20, scale = 2)
    private BigDecimal valor;


 }

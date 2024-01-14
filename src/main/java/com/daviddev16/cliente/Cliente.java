package com.daviddev16.cliente;


import com.daviddev16.pedido.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
@Table(
        name = "cliente",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_cliente_cpf", columnNames = "cpf") }
)
public class Cliente {

    @Id
    @Column(
            name = "cliente_id",
            nullable = false
    )
    @SequenceGenerator(
            name = "cliente_id_seq",
            sequenceName = "cliente_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "cliente_id_seq",
            strategy = GenerationType.SEQUENCE
    )
    private Integer id;

    @Size(min = 5, message = "{msg.cliente.nome.tamanho-min}")
    @Size(max = 50, message = "{msg.cliente.nome.tamanho-max}")
    @NotBlank(message = "{msg.cliente.nome.obrigatorio}")
    @Column(
            name = "nome",
            length = 50)
    private String nome;

    @CPF(message = "{msg.cliente.cpf.formato-invalido}")
    @NotBlank(message = "{msg.cliente.cpf.obrigatorio}")
    @Column(
            name = "cpf",
            nullable = false,
            length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(
            mappedBy = "cliente",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private Set<Pedido> pedidos;

}

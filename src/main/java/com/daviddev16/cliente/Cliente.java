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
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 5, message  = "{msg.cliente.nome.tamanho-min}")
    @Size(max = 50, message = "{msg.cliente.nome.tamanho-max}")
    @NotBlank(message = "{msg.cliente.nome.obrigatorio}")
    @Column(name = "nome", length = 50)
    private String nome;

    @CPF(message      = "{msg.cliente.cpf.formato-invalido}")
    @NotBlank(message = "{msg.cliente.cpf.obrigatorio}")
    @Column(name = "cpf", length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

}

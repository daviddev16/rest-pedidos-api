package com.daviddev16.usuario;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/* LOMBOK */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

/* JPA */
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "login")
    @NotBlank( message = "{msg.usuario.login.obrigatorio}")
    private String login;

    @Column(name = "senha")
    @NotBlank( message = "{msg.usuario.senha.obrigatorio}")
    private String senha;

    @Column(name = "ehAdmin")
    private boolean admin = false;

    public String[] cargos() {
        return admin ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};
    }

}

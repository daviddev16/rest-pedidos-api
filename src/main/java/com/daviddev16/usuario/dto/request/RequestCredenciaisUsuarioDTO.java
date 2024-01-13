package com.daviddev16.usuario.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RequestCredenciaisUsuarioDTO {

    private String login;
    private String senha;

}

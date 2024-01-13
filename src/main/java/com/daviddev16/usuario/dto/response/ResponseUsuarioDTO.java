package com.daviddev16.usuario.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ResponseUsuarioDTO {

    private String login;
    private String[] roles;

}

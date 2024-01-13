package com.daviddev16.usuario.transformer;

import com.daviddev16.usuario.Usuario;
import com.daviddev16.usuario.dto.response.ResponseTokenUsuarioDTO;
import com.daviddev16.usuario.dto.response.ResponseUsuarioDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioResponseTransformer {

    /**
     * transforma em uma ReponseDTO que não exibe informações criticas do usuário
     * */
    public ResponseUsuarioDTO transformarUsuarioEmResponseDTO(final Usuario usuario) {
        return ResponseUsuarioDTO
                .builder()
                    .login(usuario.getLogin())
                    .roles(usuario.cargos())
                .build();
    }

    public List<ResponseUsuarioDTO> transformarListaUsuarioEmResponseDTOs(final List<Usuario> usuarios) {
        return usuarios
                .stream()
                    .map(this::transformarUsuarioEmResponseDTO)
                    .collect(Collectors.toList());
    }

    public ResponseTokenUsuarioDTO criarResponseParaTokenJwt(final String tokenJwt,
                                                             final UserDetails userDetails) {
        return ResponseTokenUsuarioDTO
                .builder()
                    .webToken(tokenJwt)
                    .login(userDetails.getUsername())
                .build();
    }

}

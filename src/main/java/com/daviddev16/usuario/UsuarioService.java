package com.daviddev16.usuario;

import com.daviddev16.usuario.dto.request.RequestCredenciaisUsuarioDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService extends UserDetailsService {

    UserDetails autenticarUsuario(RequestCredenciaisUsuarioDTO credenciaisUsuarioDTO );

    Usuario incluirNovoUsuario( Usuario novoUsuario );

    void removerUsuarioPorId( Integer usuarioId );

    List<Usuario> obterTodosUsuarios();

}

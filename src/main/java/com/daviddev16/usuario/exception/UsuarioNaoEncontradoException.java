package com.daviddev16.usuario.exception;

import com.daviddev16.core.exception.GenericNotFoundException;

import static java.lang.String.*;

public final class UsuarioNaoEncontradoException extends GenericNotFoundException {

    public UsuarioNaoEncontradoException(Integer usuarioId) {
        super( format("Não foi possível localizar um usuário com id %d.", usuarioId) );
    }

    public UsuarioNaoEncontradoException(String login) {
        super( format("Login \"%s\" não encontrado.", login) );
    }
}

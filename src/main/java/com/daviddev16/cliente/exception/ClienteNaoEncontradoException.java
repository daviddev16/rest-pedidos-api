package com.daviddev16.cliente.exception;

import com.daviddev16.core.exception.GenericNotFoundException;

public final class ClienteNaoEncontradoException extends GenericNotFoundException {

    public ClienteNaoEncontradoException(Integer clienteId) {
        super( String.format("Não foi possível localizar um cliente com id %d.", clienteId) );
    }
}

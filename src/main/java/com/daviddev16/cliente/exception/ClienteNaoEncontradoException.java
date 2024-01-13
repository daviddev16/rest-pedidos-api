package com.daviddev16.cliente.exception;

import com.daviddev16.core.exception.GenericNotFoundException;

import static java.lang.String.format;

public final class ClienteNaoEncontradoException extends GenericNotFoundException {

    public ClienteNaoEncontradoException(Integer clienteId) {
        super( format("Não foi possível localizar um cliente com id %d.", clienteId) );
    }

}

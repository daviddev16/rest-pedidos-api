package com.daviddev16.pedido.exception;

import com.daviddev16.core.exception.GenericNotFoundException;

public final class PedidoNaoEncontradoException extends GenericNotFoundException {

    public PedidoNaoEncontradoException(Integer pedidoId) {
        super( String.format("Não foi possível localizar um pedido com id %d.", pedidoId) );
    }
}

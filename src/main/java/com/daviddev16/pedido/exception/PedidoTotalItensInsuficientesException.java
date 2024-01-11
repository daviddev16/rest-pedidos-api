package com.daviddev16.pedido.exception;

import com.daviddev16.core.RuntimeServiceException;

public final class PedidoTotalItensInsuficientesException extends RuntimeServiceException {

    public PedidoTotalItensInsuficientesException(Integer minimoTotalItens) {
        super( String.format("Um pedido deve conter no mínimo %d item(s).", minimoTotalItens) );
    }
}

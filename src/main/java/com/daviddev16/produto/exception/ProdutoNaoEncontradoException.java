package com.daviddev16.produto.exception;

import com.daviddev16.core.exception.GenericNotFoundException;

public final class ProdutoNaoEncontradoException extends GenericNotFoundException {

    public ProdutoNaoEncontradoException(Integer produtoId) {
        super(String.format("Não foi possível localizar um produto com id %d.", produtoId));
    }

}

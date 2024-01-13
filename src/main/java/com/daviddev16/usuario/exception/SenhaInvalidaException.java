package com.daviddev16.usuario.exception;

import com.daviddev16.core.exception.GenericNotFoundException;

public final class SenhaInvalidaException extends GenericNotFoundException {

    public SenhaInvalidaException() {
        super("Senha informada não corresponde a senha cadastrada.");
    }

}

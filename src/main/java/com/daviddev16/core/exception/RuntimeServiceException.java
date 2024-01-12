package com.daviddev16.core.exception;

/**
 * RuntimeServiceException é usado para geração de excções durante a utilização
 * de classes @Service. É uma exception genérica, que caso for lançada sozinha,
 * retornará status code BAD_REQUEST, com ApiErroDetails.
 *
 * @see com.daviddev16.core.exception.GenericNotFoundException
 *
 * */
public class RuntimeServiceException extends RuntimeException {

    public RuntimeServiceException(String message) {
        super(message);
    }

}

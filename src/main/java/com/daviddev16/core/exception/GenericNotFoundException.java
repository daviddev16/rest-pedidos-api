package com.daviddev16.core.exception;

import com.daviddev16.core.RuntimeServiceException;

public class GenericNotFoundException extends RuntimeServiceException {

    public GenericNotFoundException(String message) {
        super(message);
    }

}

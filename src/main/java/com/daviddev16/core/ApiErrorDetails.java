package com.daviddev16.core;

import java.util.Arrays;
import java.util.List;

public class ApiErrorDetails {

    private List<String> errors;

    public ApiErrorDetails(String mensagem) {
        errors = Arrays.asList(mensagem);
    }

    public ApiErrorDetails(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }


    public static ApiErrorDetails fromException(RuntimeServiceException serviceException) {
        return new ApiErrorDetails(serviceException.getMessage());
    }

}

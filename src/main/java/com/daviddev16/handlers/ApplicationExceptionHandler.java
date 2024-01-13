package com.daviddev16.handlers;

import com.daviddev16.core.ApiErrorDetails;
import com.daviddev16.core.exception.RuntimeServiceException;
import com.daviddev16.core.exception.GenericNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * RuntimeServiceException é uma exceção genérica que é lançada durante a camada de regra de
     * negócio (Service). Essa é a implementação genérica para a utilização desta exception.
     * */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeServiceException.class)
    public ApiErrorDetails handleRuntimeServiceException(RuntimeServiceException serviceException)
    {
        return ApiErrorDetails.fromException(serviceException);
    }

    /**
     * Esse handler até o momento, apenas será utilizado para informar o status code NOT_FOUND.
     * A implementação será a memsa de handleRuntimeServiceException, Porém, fica definido para
     * possíbilidade de mudanças futuramente.
     * */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GenericNotFoundException.class)
    public ApiErrorDetails handleGenericNotFoundException(GenericNotFoundException notFoundException)
    {
        return handleRuntimeServiceException(notFoundException);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorDetails handleMethodNotValidException(MethodArgumentNotValidException notValidException)
    {
        return new ApiErrorDetails(
                notValidException
                        .getBindingResult()
                        .getAllErrors()
                        .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorDetails> handleResponseStatusException(ResponseStatusException statusException)
    {
        return new ResponseEntity<>(new ApiErrorDetails(statusException.getReason()), statusException.getStatus());
    }


}

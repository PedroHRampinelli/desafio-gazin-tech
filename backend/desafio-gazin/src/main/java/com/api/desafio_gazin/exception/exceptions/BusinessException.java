package com.api.desafio_gazin.exception.exceptions;

public class BusinessException extends RuntimeException  {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException() {
        super();
    }
}

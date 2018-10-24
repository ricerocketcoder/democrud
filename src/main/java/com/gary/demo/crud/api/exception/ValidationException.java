package com.gary.demo.crud.api.exception;

public class ValidationException extends DemoCrudException {

    private static final long serialVersionUID = 1L;


    public ValidationException(String msg) {
        super(msg);
    }
}

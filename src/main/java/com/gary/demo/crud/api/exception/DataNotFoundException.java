package com.gary.demo.crud.api.exception;

import org.springframework.validation.BindingResult;

public class DataNotFoundException extends DemoCrudException {

    private static final long serialVersionUID = 1L;


    public DataNotFoundException(String msg) {
        super(msg);
    }
}

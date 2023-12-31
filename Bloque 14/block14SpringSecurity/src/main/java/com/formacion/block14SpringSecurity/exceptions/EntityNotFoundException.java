package com.formacion.block14SpringSecurity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    private Date timestamp = new Date();
    public EntityNotFoundException() {
    }

    public CustomError getEntityNotFoundException() {
        return new CustomError(timestamp, HttpStatus.NOT_FOUND.value(), "NOT FOUND: no se encuentra el registro");
    }
}

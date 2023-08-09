package com.formacion.block7crudvalidationparte1.Excepciones;

public class UnprocessableEntityException extends RuntimeException{

    public UnprocessableEntityException(String message) {
        super(message);
    }

}

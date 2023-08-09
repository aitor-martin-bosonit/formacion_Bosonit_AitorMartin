package com.formacion.block7crudvalidationparte1.Excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomError extends RuntimeException {

    private Instant timestamp;
    private int httpCode;
    private String mensaje;

}

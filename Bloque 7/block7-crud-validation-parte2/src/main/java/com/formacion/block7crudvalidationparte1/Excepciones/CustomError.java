package com.formacion.block7crudvalidationparte1.Excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomError {

    private Date timestamp;
    private int httpCode;
    private String mensaje;

}

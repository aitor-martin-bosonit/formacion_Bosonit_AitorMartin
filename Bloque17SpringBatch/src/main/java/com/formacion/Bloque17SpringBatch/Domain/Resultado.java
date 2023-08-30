package com.formacion.Bloque17SpringBatch.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resultado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResultado;
    private String localidad;
    private Integer mes;
    private Integer anio;
    private Integer numeroMediciones;
    private float temperaturaMedia;
    private String riesgo;
}

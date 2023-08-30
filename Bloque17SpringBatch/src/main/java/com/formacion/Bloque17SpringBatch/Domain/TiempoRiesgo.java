package com.formacion.Bloque17SpringBatch.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TiempoRiesgo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTiempoRiesgo;
    private Date fechaPrediccion;
    private String riesgo;
    @OneToOne
    @JoinColumn(name = "idTiempo")
    private Tiempo tiempo;
}

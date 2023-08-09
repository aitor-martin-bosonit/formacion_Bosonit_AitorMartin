package com.example.jpacascade.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LineaFra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer idFra;
    @Column(nullable = false)
    private String producto;
    private Double cantidad;
    private Double importe=0.0;
    private Double precio;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "cabecera_fra_id")
    private CabeceraFra cabeceraFra;

}

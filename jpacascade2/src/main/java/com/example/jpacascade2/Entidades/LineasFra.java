package com.example.jpacascade2.Entidades;

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
@Table(name = "lineas_fra")
public class LineasFra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idFra;

    @ManyToOne
    @JoinColumn(name = "cabeceraFra_id")
    private CabeceraFra cabeceraFra;

    private String proNomb;

    private double cantidad;

    private double importe;


}

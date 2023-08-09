package com.example.jpacascade2.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cabecera_fra")
public class CabeceraFra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int codigoCliente;

    @OneToMany(mappedBy = "cabeceraFra", cascade = CascadeType.ALL)
    private List<LineasFra> lineasFraList;


}

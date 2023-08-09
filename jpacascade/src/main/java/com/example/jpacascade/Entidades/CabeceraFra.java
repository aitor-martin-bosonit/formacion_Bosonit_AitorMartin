package com.example.jpacascade.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cabecera_Fra")
public class CabeceraFra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "codigo_cliente")
    private Cliente cliente;

    @Column(name = "importe_fra")
    private Double importeFra;

    @OneToMany(mappedBy = "cabeceraFra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaFra> lineasFra = new ArrayList<>();

    public void addLinea(LineaFra linea) {
        lineasFra.add(linea);
        linea.setCabeceraFra(this);
    }

    public void removeLinea(LineaFra linea) {
        lineasFra.remove(linea);
        linea.setCabeceraFra(null);
    }



}

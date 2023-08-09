package com.example.block11uploaddownloadfiles.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Fichero {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Date fechaSubida;
    @Column(nullable = false)
    private String categoria;

}

package com.example.jpacascade2.Repositorios;

import com.example.jpacascade2.Entidades.CabeceraFra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<CabeceraFra, Integer> {

    // Implementar consultas específicas si es necesario

}

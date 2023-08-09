package com.example.jpacascade.Repository;

import com.example.jpacascade.Entidades.LineaFra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaRepository extends JpaRepository<LineaFra, Integer> {
}

package com.example.jpacascade.Repository;

import com.example.jpacascade.Entidades.CabeceraFra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<CabeceraFra, Integer> {


}

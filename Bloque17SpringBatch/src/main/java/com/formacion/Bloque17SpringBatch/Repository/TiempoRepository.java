package com.formacion.Bloque17SpringBatch.Repository;

import com.formacion.Bloque17SpringBatch.Domain.Tiempo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiempoRepository extends JpaRepository<Tiempo, Integer> {
}

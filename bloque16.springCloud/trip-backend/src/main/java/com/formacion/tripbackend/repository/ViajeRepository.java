package com.formacion.tripbackend.repository;

import com.formacion.tripbackend.domain.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
}

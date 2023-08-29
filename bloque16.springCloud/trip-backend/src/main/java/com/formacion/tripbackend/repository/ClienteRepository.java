package com.formacion.tripbackend.repository;

import com.formacion.tripbackend.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}

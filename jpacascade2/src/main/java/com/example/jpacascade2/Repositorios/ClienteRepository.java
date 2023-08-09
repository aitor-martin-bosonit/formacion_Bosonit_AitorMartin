package com.example.jpacascade2.Repositorios;

import com.example.jpacascade2.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Implementar consultas específicas si es necesario


}

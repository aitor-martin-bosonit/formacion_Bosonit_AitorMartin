package com.formacion.API_Rest_Clientes.Repositorios;

import com.formacion.API_Rest_Clientes.Modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {



}

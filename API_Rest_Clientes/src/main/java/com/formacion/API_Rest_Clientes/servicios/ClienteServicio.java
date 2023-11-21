package com.formacion.API_Rest_Clientes.servicios;

import com.formacion.API_Rest_Clientes.Modelos.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClienteServicio {

    public List<Cliente> obtenerTodo();

    public Cliente guardar(Cliente cliente);

    public Cliente obtenerPorId(long id);

    public void eliminar(long id);





}

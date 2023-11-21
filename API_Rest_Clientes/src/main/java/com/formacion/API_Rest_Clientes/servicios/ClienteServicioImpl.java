package com.formacion.API_Rest_Clientes.servicios;

import com.formacion.API_Rest_Clientes.Modelos.Cliente;
import com.formacion.API_Rest_Clientes.Repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    @Autowired
    ClienteRepositorio clienteRepositorio;



    @Override
    public List<Cliente> obtenerTodo() {
        return clienteRepositorio.findAll();
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @Override
    public Cliente obtenerPorId(long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminar(long id) {
        clienteRepositorio.deleteById(id);
    }
}

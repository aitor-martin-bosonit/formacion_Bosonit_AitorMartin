package com.formacion.API_Rest_Clientes.Controladores;

import com.formacion.API_Rest_Clientes.Modelos.Cliente;
import com.formacion.API_Rest_Clientes.servicios.ClienteServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteControlador {
    @Autowired
    ClienteServicioImpl clienteservicio;


    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes(){
        return clienteservicio.obtenerTodo();
    }


    @PostMapping("/guardar")
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente){
        Cliente nuevo_cliente = clienteservicio.guardar(cliente);
        return new ResponseEntity<>(nuevo_cliente, HttpStatus.CREATED);
    }

    @GetMapping("/Cliente/{id}")
    public ResponseEntity<Cliente> obtenerClienteId(@PathVariable long id){
        Cliente clientePorId = clienteservicio.obtenerPorId(id);
        return ResponseEntity.ok(clientePorId);
    }

    @PutMapping("/Cliente/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable long id, @RequestBody Cliente cliente){
        Cliente clientePorId = clienteservicio.obtenerPorId(id);
        clientePorId.setNombre(cliente.getNombre());
        clientePorId.setApellido(cliente.getApellido());
        clientePorId.setEmail(cliente.getEmail());

        Cliente cliente_actualizado = clienteservicio.guardar(clientePorId);
        return new ResponseEntity<>(cliente_actualizado, HttpStatus.CREATED);
    }

    @DeleteMapping("/Cliente/{id}")
    public ResponseEntity<HashMap<String,Boolean>> eliminarCliente(@PathVariable long id){
        this.clienteservicio.eliminar(id);

        HashMap<String, Boolean> estadoClienteEliminado = new HashMap<>();
        estadoClienteEliminado.put("eliminado", true);
        return ResponseEntity.ok(estadoClienteEliminado);
    }

}

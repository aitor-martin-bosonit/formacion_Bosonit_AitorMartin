package com.formacion.tripbackend.controller;

import com.formacion.tripbackend.application.ClienteService;
import com.formacion.tripbackend.controller.dtos.input.ClienteInput;
import com.formacion.tripbackend.controller.dtos.output.ClienteOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteOutput> addCliente(@RequestBody ClienteInput clienteInput){
        return ResponseEntity.ok(clienteService.addCliente(clienteInput));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteOutput> getCliente(@PathVariable int id) {
        return ResponseEntity.ok(clienteService.getCliente(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCliente(@RequestParam int id){
        clienteService.deleteCliente(id);
        return ResponseEntity.ok("El cliente con id: " + id + " ha sido eliminado");
    }

    @GetMapping
    public Iterable<ClienteOutput> getAllCliente(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "4") int pageSize) {
        return clienteService.getAllCliente(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteOutput> updateCliente(@PathVariable Integer id, @RequestBody ClienteInput clienteInput){
        return ResponseEntity.ok(clienteService.updateCliente(id, clienteInput));
    }

    @GetMapping("/count/{idViaje}")
    public ResponseEntity<Integer> countPasajeros(@PathVariable Integer idViaje){
        return ResponseEntity.ok(clienteService.countPasajeros(idViaje));
    }

}

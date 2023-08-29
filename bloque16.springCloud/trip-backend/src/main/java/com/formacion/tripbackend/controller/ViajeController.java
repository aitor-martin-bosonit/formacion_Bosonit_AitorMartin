package com.formacion.tripbackend.controller;

import com.formacion.tripbackend.application.ViajeService;
import com.formacion.tripbackend.controller.dtos.input.ViajeInput;
import com.formacion.tripbackend.controller.dtos.output.ViajeOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @PostMapping
    public ResponseEntity<ViajeOutput> addViaje(@RequestBody ViajeInput viajeInput){
        return ResponseEntity.ok(viajeService.addViaje(viajeInput));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViajeOutput> getViaje(@PathVariable int id) {
        return ResponseEntity.ok(viajeService.getViaje(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteViaje(@RequestParam int id){
        viajeService.deleteViaje(id);
        return ResponseEntity.ok("El viaje con id: " + id + " ha sido eliminado");
    }

    @GetMapping
    public Iterable<ViajeOutput> getAllViaje(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return viajeService.getAllViaje(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViajeOutput> updateViaje(@PathVariable Integer id, @RequestBody ViajeInput viajeInput){
        return ResponseEntity.ok(viajeService.updateViaje(id, viajeInput));
    }

    @PostMapping("/addPasajero/{idViaje}/{idPasajero}")
    public ResponseEntity<ViajeOutput> addPasajero(@PathVariable Integer idViaje, @PathVariable Integer idPasajero){
        return ResponseEntity.ok(viajeService.addPasajero(idViaje, idPasajero));
    }

    @PutMapping("/{idViaje}/{estado}")
    public ResponseEntity<ViajeOutput> modifyStatus(@PathVariable Integer idViaje, @PathVariable String estado){
        return ResponseEntity.ok(viajeService.modifyEstado(idViaje, estado));
    }

    @GetMapping("/verify/{idViaje}")
    public ResponseEntity<String> verifyViaje(@PathVariable Integer idViaje){
        return ResponseEntity.ok(viajeService.verifyViaje(idViaje));
    }

}

package com.example.jpacascade.Controllers;

import com.example.jpacascade.Controllers.Dto.FacturaOutputDto;
import com.example.jpacascade.Controllers.Dto.LineaInputDto;
import com.example.jpacascade.Services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    private final FacturaService facturaService;

    @Autowired
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List<FacturaOutputDto>> getAllFacturas() {
        List<FacturaOutputDto> facturas = facturaService.getAllFacturas();
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

    @DeleteMapping("/{idFra}")
    public ResponseEntity<Void> deleteFactura(@PathVariable int idFra) {
        boolean deleted = facturaService.deleteFactura(idFra);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/linea/{idFra}")
    public ResponseEntity<FacturaOutputDto> addLineaToFactura(@PathVariable int idFra, @RequestBody LineaInputDto lineaInputDto) {
        FacturaOutputDto facturaOutputDto = facturaService.addLineaToFactura(idFra, lineaInputDto);
        return facturaOutputDto != null ? ResponseEntity.ok(facturaOutputDto) : ResponseEntity.notFound().build();
    }



}

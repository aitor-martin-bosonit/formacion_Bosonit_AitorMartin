package com.example.jpacascade2.Controllers;

import com.example.jpacascade2.Controllers.dto.FacturaOutputDto;
import com.example.jpacascade2.Controllers.dto.LineaInputDto;
import com.example.jpacascade2.Servicios.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FacturaController {

    private final FacturaService facturaService;

    @Autowired
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping("/factura")
    public ResponseEntity<List<FacturaOutputDto>> getAllFacturas() {
        List<FacturaOutputDto> facturas = facturaService.getAllFacturas();
        return ResponseEntity.ok(facturas);
    }

    @DeleteMapping("/factura/{idFra}")
    public ResponseEntity<Void> deleteFactura(@PathVariable int idFra) {
        boolean success = facturaService.deleteFactura(idFra);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/factura/linea/{idFra}")
    public ResponseEntity<FacturaOutputDto> addLineaToFactura(@PathVariable int idFra, @RequestBody LineaInputDto lineaInputDto) {
        FacturaOutputDto facturaOutputDto = facturaService.addLineaToFactura(idFra, lineaInputDto);
        return facturaOutputDto != null ? ResponseEntity.ok(facturaOutputDto) : ResponseEntity.notFound().build();
    }


}

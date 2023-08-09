package com.example.jpacascade2.Servicios;

import com.example.jpacascade2.Controllers.dto.FacturaOutputDto;
import com.example.jpacascade2.Controllers.dto.LineaInputDto;
import com.example.jpacascade2.Repositorios.ClienteRepository;
import com.example.jpacascade2.Repositorios.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {


    private final FacturaRepository facturaRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public FacturaService(FacturaRepository facturaRepository, ClienteRepository clienteRepository) {
        this.facturaRepository = facturaRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<FacturaOutputDto> getAllFacturas() {
        return null;
    }

    public boolean deleteFactura(int idFra) {
        return false;
    }

    public FacturaOutputDto addLineaToFactura(int idFra, LineaInputDto lineaInputDto) {
        return null;
    }


}

package com.example.jpacascade2.Servicios;

import com.example.jpacascade2.Controllers.dto.ClienteOutputDto;
import com.example.jpacascade2.Controllers.dto.FacturaOutputDto;
import com.example.jpacascade2.Controllers.dto.LineaInputDto;
import com.example.jpacascade2.Controllers.dto.LineaOutputDto;
import com.example.jpacascade2.Entidades.CabeceraFra;
import com.example.jpacascade2.Entidades.Cliente;
import com.example.jpacascade2.Entidades.LineasFra;
import com.example.jpacascade2.Repositorios.ClienteRepository;
import com.example.jpacascade2.Repositorios.FacturaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public FacturaServiceImpl(FacturaRepository facturaRepository, ClienteRepository clienteRepository) {
        this.facturaRepository = facturaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<FacturaOutputDto> getAllFacturas() {
        List<CabeceraFra> facturas = facturaRepository.findAll();

        List<FacturaOutputDto> facturaOutputDtos = new ArrayList<>();
        for (CabeceraFra cabeceraFra : facturas) {
            FacturaOutputDto facturaOutputDto = new FacturaOutputDto();
            facturaOutputDto.setId(cabeceraFra.getId());

            // Calcular el importe total de la factura sumando el importe de todas las líneas
            double importeFra = cabeceraFra.getLineasFraList().stream()
                    .mapToDouble(LineasFra::getImporte)
                    .sum();
            facturaOutputDto.setImporteFra(importeFra);

            // Obtener el cliente asociado a la factura
            Cliente cliente = Cliente.getCliente();
            ClienteOutputDto clienteOutputDto = new ClienteOutputDto();
            clienteOutputDto.setId(cliente.getId());
            clienteOutputDto.setNombre(cliente.getNombre());
            facturaOutputDto.setClienteOutputDto(clienteOutputDto);

            // Obtener las líneas de la factura y mapearlas a objetos LineaOutputDto
            List<LineaOutputDto> lineaOutputDtos = cabeceraFra.getLineasFraList().stream()
                    .map(linea -> {
                        LineaOutputDto lineaOutputDto = new LineaOutputDto();
                        lineaOutputDto.setId(linea.getId());
                        lineaOutputDto.setProducto(linea.getProNomb());
                        lineaOutputDto.setCantidad(linea.getCantidad());
                        lineaOutputDto.setImporte(linea.getImporte());
                        return lineaOutputDto;
                    })
                    .collect(Collectors.toList());
            facturaOutputDto.setLineaOutputDtoList(lineaOutputDtos);

            facturaOutputDtos.add(facturaOutputDto);
        }

        return facturaOutputDtos;
    }

    @Override
    public boolean deleteFactura(int idFra) throws EntityNotFoundException {
        CabeceraFra cabeceraFra = facturaRepository.findById(idFra)
                .orElseThrow(() -> new EntityNotFoundException("La factura con ID: " + idFra + " no fue encontrada."));

        facturaRepository.delete(cabeceraFra);
        return false;
    }

    @Override
    public FacturaOutputDto addLineaToFactura(int idFra, LineaInputDto lineaInputDto) throws EntityNotFoundException {
        CabeceraFra cabeceraFra = facturaRepository.findById(idFra)
                .orElseThrow(() -> new EntityNotFoundException("La factura con ID: " + idFra + " no fue encontrada."));

        LineasFra lineaFra = new LineasFra();
        lineaFra.setProNomb(lineaInputDto.getProducto());
        lineaFra.setCantidad(lineaInputDto.getCantidad());
        lineaFra.setPrecio(lineaInputDto.getImporte());

        cabeceraFra.getLineas().add(lineaFra);
        lineaFra.setCabeceraFra(cabeceraFra);

        facturaRepository.save(cabeceraFra);

        return getFacturaById(idFra);
    }

    @Override
    public FacturaOutputDto getFacturaById(int idFra) throws EntityNotFoundException {
        CabeceraFra cabeceraFra = facturaRepository.findById(idFra)
                .orElseThrow(() -> new EntityNotFoundException("La factura con ID: " + idFra + " no fue encontrada."));

        return mapToFacturaOutputDto(cabeceraFra);
    }


}

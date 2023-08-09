package com.example.jpacascade.Services;

import com.example.jpacascade.Controllers.Dto.*;
import com.example.jpacascade.Entidades.CabeceraFra;
import com.example.jpacascade.Entidades.Cliente;
import com.example.jpacascade.Entidades.LineaFra;
import com.example.jpacascade.Repository.ClienteRepository;
import com.example.jpacascade.Repository.FacturaRepository;
import com.example.jpacascade.Repository.LineaRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final ClienteRepository clienteRepository;
    private final LineaRepository lineaRepository;

    @Autowired
    public FacturaService(FacturaRepository facturaRepository, ClienteRepository clienteRepository, LineaRepository lineaRepository) {
        this.facturaRepository = facturaRepository;
        this.clienteRepository = clienteRepository;
        this.lineaRepository = lineaRepository;
    }

    public List<FacturaOutputDto> getAllFacturas() {
        List<CabeceraFra> facturas = facturaRepository.findAll();
        List<FacturaOutputDto> facturaOutputDtos = new ArrayList<>();

        for (CabeceraFra cabeceraFra : facturas) {
            FacturaOutputDto facturaOutputDto = new FacturaOutputDto();
            facturaOutputDto.setId(cabeceraFra.getId());
            facturaOutputDto.setImporteFra(cabeceraFra.getImporteFra());
            facturaOutputDto.setClienteOutputDto(mapToClienteOutputDto(cabeceraFra.getCliente()));
            facturaOutputDto.setLineaOutputDtoList(mapToLineaOutputDtoList(cabeceraFra.getLineasFra()));
            facturaOutputDtos.add(facturaOutputDto);
        }

        return facturaOutputDtos;
    }

    @Transactional
    public boolean deleteFactura(int idFra) {
        Optional<CabeceraFra> optionalCabeceraFra = facturaRepository.findById(idFra);

        if (optionalCabeceraFra.isPresent()) {
            CabeceraFra cabeceraFra = optionalCabeceraFra.get();
            facturaRepository.delete(cabeceraFra);
            return true;
        }

        return false;
    }
    @Transactional
    public FacturaOutputDto createFactura(FacturaImputDto facturaInputDto) {
        Cliente cliente = clienteRepository.findById(facturaInputDto.getCodigoCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        CabeceraFra cabeceraFra = new CabeceraFra();
        cabeceraFra.setCliente(cliente);
        cabeceraFra.setImporteFra(facturaInputDto.getImporteFra());

        List<LineaInputDto> lineaInputDtoList = facturaInputDto.getLineaInputDtoList();
        List<LineaFra> lineasFra = new ArrayList<>();

        for (LineaInputDto lineaInputDto : lineaInputDtoList) {
            LineaFra lineaFra = new LineaFra();
            lineaFra.setProducto(lineaInputDto.getProducto());
            lineaFra.setCantidad(lineaInputDto.getCantidad());
            lineaFra.setPrecio(lineaInputDto.getImporte());
            lineaFra.setCabeceraFra(cabeceraFra);
            lineasFra.add(lineaFra);
        }

        cabeceraFra.setLineasFra(lineasFra);

        double importeTotal = lineasFra.stream()
                .mapToDouble(linea -> linea.getCantidad() * linea.getPrecio())
                .sum();
        cabeceraFra.setImporteFra(importeTotal);

        cabeceraFra = facturaRepository.save(cabeceraFra);

        return mapToFacturaOutputDto(cabeceraFra);
    }

    @Transactional
    public FacturaOutputDto addLineaToFactura(int idFra, LineaInputDto lineaInputDto) {
        Optional<CabeceraFra> optionalCabeceraFra = facturaRepository.findById(idFra);

        if (optionalCabeceraFra.isPresent()) {
            CabeceraFra cabeceraFra = optionalCabeceraFra.get();

            LineaFra lineaFra = new LineaFra();
            lineaFra.setProducto(lineaInputDto.getProducto());
            lineaFra.setCantidad(lineaInputDto.getCantidad());
            lineaFra.setImporte(lineaInputDto.getImporte());
            lineaFra.setCabeceraFra(cabeceraFra);

            lineaRepository.save(lineaFra);

            return mapToFacturaOutputDto(cabeceraFra);
        }

        return null;
    }

    private ClienteOutputDto mapToClienteOutputDto(Cliente cliente) {
        ClienteOutputDto clienteOutputDto = new ClienteOutputDto();
        clienteOutputDto.setId(cliente.getCodigoCliente());
        clienteOutputDto.setNombre(cliente.getNombre());
        return clienteOutputDto;
    }

    private List<LineaOutputDto> mapToLineaOutputDtoList(List<LineaFra> lineasFra) {
        List<LineaOutputDto> lineaOutputDtos = new ArrayList<>();

        for (LineaFra lineaFra : lineasFra) {
            LineaOutputDto lineaOutputDto = new LineaOutputDto();
            lineaOutputDto.setId(lineaFra.getId());
            lineaOutputDto.setProducto(lineaFra.getProducto());
            lineaOutputDto.setCantidad(lineaFra.getCantidad());
            lineaOutputDto.setImporte(lineaFra.getImporte());
            lineaOutputDtos.add(lineaOutputDto);
        }

        return lineaOutputDtos;
    }

    private FacturaOutputDto mapToFacturaOutputDto(CabeceraFra cabeceraFra) {
        FacturaOutputDto facturaOutputDto = new FacturaOutputDto();
        facturaOutputDto.setId(cabeceraFra.getId());
        facturaOutputDto.setImporteFra(cabeceraFra.getImporteFra());
        facturaOutputDto.setClienteOutputDto(mapToClienteOutputDto(cabeceraFra.getCliente()));
        facturaOutputDto.setLineaOutputDtoList(mapToLineaOutputDtoList(cabeceraFra.getLineasFra()));
        return facturaOutputDto;
    }

}



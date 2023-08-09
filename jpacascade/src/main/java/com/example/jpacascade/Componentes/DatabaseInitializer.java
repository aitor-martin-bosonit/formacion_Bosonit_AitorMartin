package com.example.jpacascade.Componentes;

import com.example.jpacascade.Controllers.Dto.*;
import com.example.jpacascade.Services.ClienteService;
import com.example.jpacascade.Services.FacturaService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseInitializer {

        private final ClienteService clienteService;
        private final FacturaService facturaService;

        @Autowired
        public DatabaseInitializer(ClienteService clienteService, FacturaService facturaService) {
            this.clienteService = clienteService;
            this.facturaService = facturaService;
        }

        @PostConstruct
        public void initializeData() {
            // Meter un cliente nuevo
            ClienteInputDto clienteInputDto = new ClienteInputDto();
            clienteInputDto.setNombre("Tiburcio");
            ClienteOutputDto clienteOutputDto = clienteService.createCliente(clienteInputDto);

            // Crear una factura cliente de prueba
            FacturaImputDto facturaInputDto = new FacturaImputDto();
            facturaInputDto.setCodigoCliente(clienteOutputDto.getId());

            LineaInputDto linea1 = new LineaInputDto();
            linea1.setProducto("Monitor ASUS W303");
            linea1.setCantidad(5.0);
            linea1.setImporte(100.0);

            LineaInputDto linea2 = new LineaInputDto();
            linea2.setProducto("Teclado Corssair K0123");
            linea2.setCantidad(2.0);
            linea2.setImporte(50.0);

            facturaInputDto.setLineaInputDtoList(Arrays.asList(linea1, linea2));

            FacturaOutputDto facturaOutputDto = facturaService.createFactura(facturaInputDto);

        }

    }


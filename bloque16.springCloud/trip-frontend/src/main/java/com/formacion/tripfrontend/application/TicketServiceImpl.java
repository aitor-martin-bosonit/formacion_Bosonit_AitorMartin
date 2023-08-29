package com.formacion.tripfrontend.application;

import com.formacion.tripfrontend.controller.dtos.input.TicketInput;
import com.formacion.tripfrontend.controller.dtos.output.TicketOutput;
import com.formacion.tripfrontend.domain.Cliente;
import com.formacion.tripfrontend.domain.Ticket;
import com.formacion.tripfrontend.domain.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    RestTemplate restTemplate;

    private final String CLIENTE_URL = "http://localhost:8080/cliente/{userId}";
    private final String VIAJE_URL = "http://localhost:8080/viaje/{viajeId}";

    @Override
    public TicketOutput generateTicket(int userId, int viajeId) {
        Cliente cliente = restTemplate.getForObject(CLIENTE_URL, Cliente.class, userId);
        Viaje viaje = restTemplate.getForObject(VIAJE_URL, Viaje.class, viajeId);

        TicketInput ticketInput = new TicketInput(
                cliente.getIdCliente(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail(),
                viaje.getOrigen(), viaje.getDestino(), viaje.getHoraSalida(), viaje.getHoraLlegada()
        );

        return ticketRepository.save(new Ticket(ticketInput)).ticketToTicketOutput();
    }
}

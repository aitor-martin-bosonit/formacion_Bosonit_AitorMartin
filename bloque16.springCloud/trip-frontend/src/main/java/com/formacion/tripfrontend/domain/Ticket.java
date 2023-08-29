package com.formacion.tripfrontend.domain;

import com.formacion.tripfrontend.controller.dtos.input.TicketInput;
import com.formacion.tripfrontend.controller.dtos.output.TicketOutput;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private int id;
    private int idPasajero;
    private String nombrePasajero;
    private String apellidoPasajero;
    private String emailPasajero;
    private String origenViaje;
    private String destinoViaje;
    private String horaSalida;
    private String horaLlegada;

    public Ticket(TicketInput ticketInput){
        BeanUtils.copyProperties(ticketInput, this);
    }

    public TicketOutput ticketToTicketOutput(){
        return new TicketOutput(id, idPasajero, nombrePasajero, apellidoPasajero, emailPasajero, origenViaje, destinoViaje, horaSalida, horaLlegada);
    }
}

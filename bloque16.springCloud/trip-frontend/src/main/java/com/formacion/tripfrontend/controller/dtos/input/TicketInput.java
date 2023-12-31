package com.formacion.tripfrontend.controller.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketInput {

    private int idPasajero;
    private String nombrePasajero;
    private String apellidoPasajero;
    private String emailPasajero;
    private String origenViaje;
    private String destinoViaje;
    private String horaSalida;
    private String horaLlegada;

}

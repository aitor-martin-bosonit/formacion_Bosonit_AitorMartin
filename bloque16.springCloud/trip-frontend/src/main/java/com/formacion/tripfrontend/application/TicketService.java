package com.formacion.tripfrontend.application;

import com.formacion.tripfrontend.controller.dtos.output.TicketOutput;

public interface TicketService {
    TicketOutput generateTicket(int userId, int viajeId);
}

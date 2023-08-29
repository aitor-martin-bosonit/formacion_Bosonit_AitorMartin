package com.formacion.tripfrontend.controller;

import com.formacion.tripfrontend.application.TicketService;
import com.formacion.tripfrontend.controller.dtos.output.TicketOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/generate/{userId}/{viajeId}")
    public ResponseEntity<TicketOutput> generateTicket(@PathVariable int userId, @PathVariable int viajeId) {
        return ResponseEntity.ok(ticketService.generateTicket(userId, viajeId));
    }
}

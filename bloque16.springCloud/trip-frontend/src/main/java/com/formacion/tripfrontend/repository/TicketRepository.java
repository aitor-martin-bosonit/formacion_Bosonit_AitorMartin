package com.formacion.tripfrontend.repository;

import com.formacion.tripfrontend.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}

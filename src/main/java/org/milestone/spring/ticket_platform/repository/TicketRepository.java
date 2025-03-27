package org.milestone.spring.ticket_platform.repository;

import java.util.Optional;

import org.milestone.spring.ticket_platform.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    public Optional<Ticket> findById(Integer id);
}
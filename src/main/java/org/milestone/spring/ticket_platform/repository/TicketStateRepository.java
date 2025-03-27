package org.milestone.spring.ticket_platform.repository;

import java.util.List;
import java.util.Optional;

import org.milestone.spring.ticket_platform.model.TicketState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStateRepository extends JpaRepository<TicketState, Integer> {
    
    public List<TicketState> findAll();
 
    public Optional<TicketState> findByName(String name);
}

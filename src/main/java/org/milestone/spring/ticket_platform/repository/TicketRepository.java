package org.milestone.spring.ticket_platform.repository;

import java.util.List;
import java.util.Optional;

import org.milestone.spring.ticket_platform.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    public Optional<Ticket> findById(Integer id);
    
    public List<Ticket> findByCategoryName(String categoryName);
 
    public List<Ticket> findByStateName(String stateName);
 
    List<Ticket> findByTitleContainingIgnoreCase(String title);
}
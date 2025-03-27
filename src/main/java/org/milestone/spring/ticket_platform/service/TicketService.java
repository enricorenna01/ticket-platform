package org.milestone.spring.ticket_platform.service;

import java.util.List;

import org.milestone.spring.ticket_platform.model.Ticket;
import org.milestone.spring.ticket_platform.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository repository;

    public List<Ticket> findAll(){
         return repository.findAll();
     }
 
     public void save(Ticket ticket){
         repository.save(ticket);
     }
}

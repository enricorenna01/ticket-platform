package org.milestone.spring.ticket_platform.service;

import java.time.LocalDate;
import java.util.List;

import org.milestone.spring.ticket_platform.model.Ticket;
import org.milestone.spring.ticket_platform.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository repository;

    @Autowired
     private TicketStateService ticketStateService;

    public List<Ticket> findAll(){
        return repository.findAll();
    }

    public Ticket findById(Integer id){
        return repository.findById(id).get();
    }
 
    public void save(Ticket ticket){
        repository.save(ticket);
    }

    public Ticket newTicket(){
        Ticket ticket = new Ticket();
        ticket.setCreationDate(LocalDate.now());
        ticket.setState(ticketStateService.findByName("to do"));
        return ticket;
     }

     public void delete(Ticket ticket){
        repository.delete(ticket);
    }
}

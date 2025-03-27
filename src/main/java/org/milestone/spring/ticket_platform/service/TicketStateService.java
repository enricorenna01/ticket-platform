package org.milestone.spring.ticket_platform.service;

import java.util.List;

import org.milestone.spring.ticket_platform.model.TicketState;
import org.milestone.spring.ticket_platform.repository.TicketStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
 public class TicketStateService {
     
 
    @Autowired
    private TicketStateRepository repository;
     
 
    public List<TicketState> findAll(){
        return repository.findAll();
    }

    public TicketState findByName(String name){
        return repository.findByName(name).get();
    }
 }

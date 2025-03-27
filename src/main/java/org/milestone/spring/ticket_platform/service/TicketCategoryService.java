package org.milestone.spring.ticket_platform.service;

import java.util.List;

import org.milestone.spring.ticket_platform.model.TicketCategory;
import org.milestone.spring.ticket_platform.repository.TicketCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketCategoryService {
     
    @Autowired
    private TicketCategoryRepository repository;
 
    public List<TicketCategory> findAll(){
        return repository.findAll();
    }
 }
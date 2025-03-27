package org.milestone.api;

import java.util.List;

import org.milestone.spring.ticket_platform.model.Ticket;
import org.milestone.spring.ticket_platform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/tickets")
public class TicketRestController {
     
    @Autowired
    private TicketService service;
 
    @GetMapping
    public List<Ticket> index(){
        return service.findAll();
    }
 
    @GetMapping("{category}/category")
    public List<Ticket> findByCategory(@RequestParam String category) {
        return service.findByCategory(category);
    }
 
    @GetMapping("{state}/state")
    public List<Ticket> findByState(@RequestParam String state) {
        return service.findByState(state);
    }
}
     
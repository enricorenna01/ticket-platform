package org.milestone.spring.ticket_platform.controller;

import java.util.List;

import org.milestone.spring.ticket_platform.model.Ticket;
import org.milestone.spring.ticket_platform.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/ticket")
public class TicketController {
    
    @Autowired
    private TicketRepository repository;

    @GetMapping()
    public String index(Model model) {
        List<Ticket> tickets = repository.findAll();
        model.addAttribute("tickets", tickets);
        return "tickets/index";
    }
    
}

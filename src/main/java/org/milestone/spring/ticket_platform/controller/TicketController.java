package org.milestone.spring.ticket_platform.controller;

import java.util.List;

import org.milestone.spring.ticket_platform.model.Ticket;
import org.milestone.spring.ticket_platform.service.TicketCategoryService;
import org.milestone.spring.ticket_platform.service.TicketService;
import org.milestone.spring.ticket_platform.service.TicketStateService;
import org.milestone.spring.ticket_platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/ticket")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;
 
    @Autowired
    private TicketCategoryService categoryService;
 
    @Autowired
    private UserService userService;
     
    @Autowired
    private TicketStateService stateService;

    @GetMapping
    public String tickets(Model model) {
        List<Ticket> tickets = ticketService.findAll();
        System.out.println(tickets);
        model.addAttribute("tickets", ticketService.findAll());
        return "tickets/index";
    }
    
    @GetMapping("/create")
     public String formTicket(Model model) {
         model.addAttribute("ticket", ticketService.newTicket());
         model.addAttribute("categories", categoryService.findAll());
         model.addAttribute("users", userService.findAll());
         model.addAttribute("toDoState", stateService.findByName("to do"));
         return "tickets/create-edit";
     }
     
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ticket") Ticket formTicket, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
 
        if (bindingResult.hasErrors()) {
            model.addAttribute("ticket", formTicket);
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("users", userService.findAll());
            model.addAttribute("toDoState", stateService.findByName("to do"));
            return "tickets/create-edit";
        }
        ticketService.save(formTicket);
         
        return "redirect:/ticket";
     }

     @GetMapping("/{id}")
     public String showPizza(@PathVariable Integer id, Model model) {
         Ticket ticket = ticketService.findById(id);
         model.addAttribute("ticket", ticket);
         model.addAttribute("user", ticket.getOperator());
         model.addAttribute("state", ticket.getState().getName());
         model.addAttribute("category", ticket.getCategory().getName());
         System.out.println(ticket.getState().getName());
 
         return "tickets/show";
     }
}


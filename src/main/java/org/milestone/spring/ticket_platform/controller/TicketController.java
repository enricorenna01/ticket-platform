package org.milestone.spring.ticket_platform.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.milestone.spring.ticket_platform.model.Note;
import org.milestone.spring.ticket_platform.model.Ticket;
import org.milestone.spring.ticket_platform.model.User;
import org.milestone.spring.ticket_platform.service.NoteService;
import org.milestone.spring.ticket_platform.service.TicketCategoryService;
import org.milestone.spring.ticket_platform.service.TicketService;
import org.milestone.spring.ticket_platform.service.TicketStateService;
import org.milestone.spring.ticket_platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private NoteService noteService;

    @GetMapping
    public String tickets(Authentication authentication , Model model) {
 
    User user = userService.findByUsername(authentication.getName()).get();
    List<Ticket> tickets;
    
    if (user.isAdmin()) {
        tickets = ticketService.findAll();
    }else{
        tickets = user.getTickets();
    }
            
    model.addAttribute("tickets", tickets);
    model.addAttribute("user", user);
    model.addAttribute("canChange", userService.canChange(user));
    return "tickets/index";
    }
    
    @GetMapping("/create")
    public String create(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName()).get();
        model.addAttribute("ticket", ticketService.newTicket());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("users", userService.findByState(true));
        model.addAttribute("toDoState", stateService.findByName("to do"));
        model.addAttribute("user", user);
        return "tickets/create";
    }
     
    public String store(@Valid @ModelAttribute("ticket") Ticket formTicket, BindingResult bindingResult, Authentication authentication, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            User user = userService.findByUsername(authentication.getName()).get();
            model.addAttribute("user", user);
            model.addAttribute("ticket", formTicket);
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("users", userService.findByState(true));
            model.addAttribute("toDoState", stateService.findByName("to do"));
            return "tickets/create";
        }
        ticketService.save(formTicket);
         
        return "redirect:/ticket";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id,Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName()).get();
        Ticket ticket = ticketService.findById(id);
        Note newNote = new Note();
        newNote.setCreationDateTime(LocalDateTime.now());
        newNote.setTicket(ticket);
        newNote.setAuthor(user);
        

        if(!ticketService.checkUser(user, ticket)) return "redirect:/ticket";
        
        model.addAttribute("ticket", ticket);
        model.addAttribute("user", ticket.getOperator());
        model.addAttribute("state", ticket.getState().getName());
        model.addAttribute("category", ticket.getCategory().getName());
        model.addAttribute("newNote", newNote);
        model.addAttribute("notes", noteService.findByTicket(id));
        System.out.println(ticket.getState().getName());
        
 
        return "tickets/show";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        Ticket ticket = ticketService.findById(id);
        ticketService.delete(ticket);

        return "redirect:/ticket";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id,Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName()).get();
        Ticket ticket = ticketService.findById(id);
 
        model.addAttribute("ticket", ticket);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("users", userService.findByState(true));
        model.addAttribute("states", stateService.findAll());
        model.addAttribute("user", user);
 
        return "tickets/edit";
    }
 
 
    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("ticket") Ticket formTicket, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ticket", formTicket);
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("users", userService.findByState(true));
            model.addAttribute("states", stateService.findAll());
 
            return "tickets/edit";
        };
        ticketService.save(formTicket);
         
        return "redirect:/ticket";
    }

    @PostMapping("/user/{id}/state")
    public String store(@PathVariable Integer id) {
        userService.changeState(id);
        
        return "redirect:/ticket";
    }

    @GetMapping("/search")
     public String findByTitle(@RequestParam("title") String title, Authentication authentication, Model model) {
         model.addAttribute("books", ticketService.findByTitle(title));
 
         User user = userService.findByUsername(authentication.getName()).get();
         List<Ticket> tickets;
 
         tickets = ticketService.findByTitle(title);
         
         model.addAttribute("tickets", tickets);
         model.addAttribute("user", user);
         model.addAttribute("canChange", userService.canChange(user));
         return "tickets/index";
     }
}


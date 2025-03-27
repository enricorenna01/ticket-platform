package org.milestone.spring.ticket_platform.controller;

import org.milestone.spring.ticket_platform.model.Note;
import org.milestone.spring.ticket_platform.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
 @RequestMapping("/note")
 public class NoteController {
     
     @Autowired
     NoteService service;
 
     @PostMapping("/create")
     public String store(@Valid @ModelAttribute("newNote") Note formNote, Model model) {
         System.out.println("aaaaaaa");
         service.save(formNote);
         
         
         return "redirect:/ticket";
     }
 }
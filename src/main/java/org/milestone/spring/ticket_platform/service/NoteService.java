package org.milestone.spring.ticket_platform.service;

import java.util.List;

import org.milestone.spring.ticket_platform.model.Note;
import org.milestone.spring.ticket_platform.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    
    @Autowired
    private NoteRepository repository;
 
    public void save(Note note){
        repository.save(note);
    }

    public List<Note> findByTicket(Integer id){
        return repository.findByTicketIdOrderByCreationDateTimeDesc(id);
    }
}

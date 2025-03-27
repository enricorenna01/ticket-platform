package org.milestone.spring.ticket_platform.repository;

import org.milestone.spring.ticket_platform.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer>{
    
}

package org.milestone.spring.ticket_platform.repository;

import org.milestone.spring.ticket_platform.model.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Integer>{
     
}

package org.milestone.spring.ticket_platform.repository;

import org.milestone.spring.ticket_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    
}

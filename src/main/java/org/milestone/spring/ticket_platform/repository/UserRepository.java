package org.milestone.spring.ticket_platform.repository;

import java.util.List;
import java.util.Optional;

import org.milestone.spring.ticket_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    public Optional<User> findByUsername(String username);
 
    public Optional<User> findById(Integer id);

    public List<User> findByState(boolean state);
}

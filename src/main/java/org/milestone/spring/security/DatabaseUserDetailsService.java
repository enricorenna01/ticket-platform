package org.milestone.spring.security;
 
 import java.util.Optional;

import org.milestone.spring.ticket_platform.model.User;
import org.milestone.spring.ticket_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DatabaseUserDetailsService implements UserDetailsService{
     
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = repo.findByUsername(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("Username not found");
        }

        return new DatabaseUserDetails(user.get());
    }
}
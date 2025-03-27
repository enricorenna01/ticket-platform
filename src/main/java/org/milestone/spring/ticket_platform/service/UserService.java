package org.milestone.spring.ticket_platform.service;

import java.util.List;
import java.util.Optional;

import org.milestone.spring.ticket_platform.model.Ticket;
import org.milestone.spring.ticket_platform.model.User;
import org.milestone.spring.ticket_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
     
    @Autowired
    private UserRepository repository;
 
    public List<User> findAll(){
        return repository.findAll();
    }

    public Optional<User> findById(Integer id){
        return repository.findById(id);
    }

    public Optional<User> findByUsername(String username){
        return repository.findByUsername(username);
    }

    public List<User> findByState(boolean state){
        return repository.findByState(state);
    }

    public void changeState(Integer id){
         User user = repository.findById(id).get();
         if(user.getState()){
                     user.setState(false);
         }else{
             user.setState(true);
         }
         repository.save(user);
     }
 
     public boolean canChange(User user){
         List<Ticket> tickets = user.getTickets();
         if(user.getState()){
             for(Ticket ticket : tickets){
                 if (!ticket.getState().getName().equals("completed")){
                     return false;
                 }
             }
         }
         return true;
     }
 }
package org.milestone.spring.ticket_platform.service;

import java.util.List;

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
 }
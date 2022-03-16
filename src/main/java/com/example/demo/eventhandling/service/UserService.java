package com.example.demo.eventhandling.service;

import com.example.demo.eventhandling.NeedEventHandling;
import com.example.demo.eventhandling.NeedEventHandling.EventType;
import com.example.demo.eventhandling.persistence.User;
import com.example.demo.eventhandling.persistence.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserRegister, UserVisitor {

  @Autowired
  UserRepo userRepo;


  @NeedEventHandling(eventType = EventType.CREATE)
  @Override
  public User register(String name) {
    return userRepo.save(new User(name));
  }

  @NeedEventHandling(eventType = EventType.READ)
  @Override
  public User visit(String name) {
    return userRepo.findByName(name).orElseThrow(RuntimeException::new);
  }
}

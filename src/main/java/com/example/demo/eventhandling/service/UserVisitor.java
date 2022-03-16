package com.example.demo.eventhandling.service;

import com.example.demo.eventhandling.persistence.User;

public interface UserVisitor {
  User visit(String name);
}

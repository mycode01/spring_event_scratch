package com.example.demo.eventhandling.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
  Optional<User> findByName(String name);

}

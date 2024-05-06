package com.turkcell.pair1.authservice.repository;

import com.turkcell.pair1.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);
}
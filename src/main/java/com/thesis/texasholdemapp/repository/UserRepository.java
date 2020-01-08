package com.thesis.texasholdemapp.repository;

import com.thesis.texasholdemapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

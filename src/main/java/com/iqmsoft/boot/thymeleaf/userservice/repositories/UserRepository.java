package com.iqmsoft.boot.thymeleaf.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqmsoft.boot.thymeleaf.userservice.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByNameContaining(String name);
}

package com.example.tutorial.repository.user_repository;

import com.example.tutorial.domains.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    void deleteById(Long id);
}

package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.User;
import com.ucu.fintrack.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {

    @Override
    Optional<User> findById(Long id);

    @Override
    Optional<User> findByUsername(String username);

    @Override
    User save(User user);

    @Override
    void deleteById(Long id);
}

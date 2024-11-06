package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.Bank;
import com.ucu.fintrack.domain.repository.BankRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBankRepository extends JpaRepository<Bank, Long>, BankRepository {
    // Spring Data JPA proporcionará la implementación de los métodos CRUD
}

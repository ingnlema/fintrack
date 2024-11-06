package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.repository.BankAccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBankAccountRepository extends JpaRepository<BankAccount, Long>, BankAccountRepository {
    // Spring Data JPA proporcionará la implementación de los métodos CRUD
}

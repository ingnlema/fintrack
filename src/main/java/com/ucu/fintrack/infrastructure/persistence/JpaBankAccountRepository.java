package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.repository.BankAccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaBankAccountRepository extends JpaRepository<BankAccount, Long>, BankAccountRepository {

    @Override
    BankAccount save(BankAccount bankAccount);

    @Override
    Optional<BankAccount> findById(Long id);

    @Override
    List<BankAccount> findAll();

    @Override
    void deleteById(Long id);
}

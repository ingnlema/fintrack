package com.ucu.fintrack.domain.repository;
import com.ucu.fintrack.domain.entities.BankAccount;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepository {
    BankAccount save(BankAccount bankAccount);
    Optional<BankAccount> findById(Long id);
    List<BankAccount> findAll();
    void deleteById(Long id);
}

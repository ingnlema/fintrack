package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.Bank;
import com.ucu.fintrack.domain.repository.BankRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaBankRepository extends JpaRepository<Bank, Long>, BankRepository {

    @Override
    Bank save(Bank bank);

    @Override
    Optional<Bank> findById(Long id);

    @Override
    List<Bank> findAll();

    @Override
    void deleteById(Long id);
}

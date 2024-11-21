package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.IncomeSource;
import com.ucu.fintrack.domain.repository.IncomeSourceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaIncomeSourceRepository extends JpaRepository<IncomeSource, Long>, IncomeSourceRepository {

    @Override
    IncomeSource save(IncomeSource incomeSource);

    @Override
    Optional<IncomeSource> findById(Long id);

    @Override
    List<IncomeSource> findAll();

    @Override
    void deleteById(Long id);
}

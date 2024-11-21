package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.IncomeSource;
import java.util.List;
import java.util.Optional;

public interface IncomeSourceRepository {
    IncomeSource save(IncomeSource incomeSource);
    Optional<IncomeSource> findById(Long id);
    List<IncomeSource> findAll();
    void deleteById(Long id);
}

package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.IncomeRecord;
import java.util.List;
import java.util.Optional;

public interface IncomeRecordRepository {
    IncomeRecord save(IncomeRecord incomeRecord);
    Optional<IncomeRecord> findById(Long id);
    List<IncomeRecord> findAll();
    void deleteById(Long id);
}

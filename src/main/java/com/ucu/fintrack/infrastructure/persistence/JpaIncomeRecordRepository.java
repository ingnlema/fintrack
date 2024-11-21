package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.IncomeRecord;
import com.ucu.fintrack.domain.repository.IncomeRecordRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaIncomeRecordRepository extends JpaRepository<IncomeRecord, Long>, IncomeRecordRepository {

    @Override
    IncomeRecord save(IncomeRecord incomeRecord);

    @Override
    Optional<IncomeRecord> findById(Long id);

    @Override
    List<IncomeRecord> findAll();

    @Override
    void deleteById(Long id);
}

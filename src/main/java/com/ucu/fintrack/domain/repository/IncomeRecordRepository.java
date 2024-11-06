package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.IncomeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRecordRepository extends JpaRepository<IncomeRecord, Long> {
}

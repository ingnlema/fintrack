package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.IncomeSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeSourceRepository extends JpaRepository<IncomeSource, Long> {
}

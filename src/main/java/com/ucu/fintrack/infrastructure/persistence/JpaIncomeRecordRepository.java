package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.IncomeRecord;
import com.ucu.fintrack.domain.repository.IncomeRecordRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaIncomeRecordRepository extends JpaRepository<IncomeRecord, Long>, IncomeRecordRepository {

}

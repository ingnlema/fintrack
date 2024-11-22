package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.IncomeSource;
import com.ucu.fintrack.domain.repository.IncomeSourceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaIncomeSourceRepository extends JpaRepository<IncomeSource, Long>, IncomeSourceRepository {

}

package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

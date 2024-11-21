package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(Long id);
    List<Category> findAll();
    void deleteById(Long id);
}
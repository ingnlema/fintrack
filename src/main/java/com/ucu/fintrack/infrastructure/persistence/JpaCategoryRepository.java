package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.Category;
import com.ucu.fintrack.domain.repository.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Long>, CategoryRepository {

    @Override
    Category save(Category category);

    @Override
    Optional<Category> findById(Long id);

    @Override
    List<Category> findAll();

    @Override
    void deleteById(Long id);
}

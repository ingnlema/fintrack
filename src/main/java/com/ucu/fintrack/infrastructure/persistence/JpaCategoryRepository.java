package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.Category;
import com.ucu.fintrack.domain.repository.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Long>, CategoryRepository {

}

package com.nimap.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimap.api.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
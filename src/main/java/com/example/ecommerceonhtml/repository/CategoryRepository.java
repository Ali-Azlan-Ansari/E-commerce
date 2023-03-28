package com.example.ecommerceonhtml.repository;

import com.example.ecommerceonhtml.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Category findByCategoryName(String Name);
}

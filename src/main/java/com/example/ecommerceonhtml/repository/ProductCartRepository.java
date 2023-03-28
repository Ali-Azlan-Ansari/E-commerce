package com.example.ecommerceonhtml.repository;

import com.example.ecommerceonhtml.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart,Long> {
}

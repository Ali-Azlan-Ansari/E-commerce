package com.example.ecommerceonhtml.repository;

import com.example.ecommerceonhtml.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderOfCustomerRepository extends JpaRepository<Transaction,Long> {
}

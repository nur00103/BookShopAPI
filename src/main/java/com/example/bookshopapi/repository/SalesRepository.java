package com.example.bookshopapi.repository;

import com.example.bookshopapi.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
}

package com.example.bookshopapi.repository;

import com.example.bookshopapi.entity.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse,Long> {
    PublishingHouse findByHouseName(String houseName);
}

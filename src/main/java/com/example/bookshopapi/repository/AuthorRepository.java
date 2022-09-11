package com.example.bookshopapi.repository;

import com.example.bookshopapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    Author findByAuthor(String author);
}

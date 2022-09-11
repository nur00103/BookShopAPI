package com.example.bookshopapi.repository;

import com.example.bookshopapi.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Long> {
    Language findByLanguage(String language);
}

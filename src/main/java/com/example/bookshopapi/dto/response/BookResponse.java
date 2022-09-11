package com.example.bookshopapi.dto.response;

import com.example.bookshopapi.dto.request.AuthorRequest;
import com.example.bookshopapi.dto.request.LanguageRequest;
import com.example.bookshopapi.dto.request.PublishingHouseRequest;
import com.example.bookshopapi.dto.request.TypeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String name;

    private AuthorResponse authorResponse;

    private TypeResponse typeResponse;

    private LanguageResponse languageResponse;

    private PublishingHouseResponse publishingHouseResponse;

    private int page;

    private int stock;

    private double price;

    private int discount;
}

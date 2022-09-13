package com.example.bookshopapi.dto.request;

import com.example.bookshopapi.dto.response.LanguageResponse;
import com.example.bookshopapi.entity.Author;
import com.example.bookshopapi.entity.Language;
import com.example.bookshopapi.entity.PublishingHouse;
import com.example.bookshopapi.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    private String name;

    private AuthorRequest author;

    private TypeRequest type;

    private LanguageRequest language;

    private PublishingHouseRequest pubHouse;

    private int page;

    private int stock;

    private double price;

    private int discount;
}

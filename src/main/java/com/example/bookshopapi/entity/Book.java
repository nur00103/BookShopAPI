package com.example.bookshopapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @OneToOne
    @JoinColumn(name = "pub_house_id")
    private PublishingHouse publishingHouse;


    private int page;

    private int stock;

    private double price;

    private int discount;
}

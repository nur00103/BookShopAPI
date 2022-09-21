package com.example.bookshopapi.dto.response;

import com.example.bookshopapi.entity.Book;
import com.example.bookshopapi.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesResponse {

    private Long id;
    private Customer customer;
    private Book book;
    private int quantity;
    private double amount;
    private Date salesDate;
}

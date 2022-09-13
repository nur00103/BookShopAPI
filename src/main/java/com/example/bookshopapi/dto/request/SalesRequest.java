package com.example.bookshopapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesRequest {

    private Long customer;
    private Long book;
    private int quantity;
    private double amount;

}

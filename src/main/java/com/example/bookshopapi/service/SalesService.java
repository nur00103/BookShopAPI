package com.example.bookshopapi.service;

import com.example.bookshopapi.dto.request.SalesRequest;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.dto.response.SalesResponse;

import java.util.List;

public interface SalesService {
    ResponseModel<List<SalesResponse>> getAllSales();

    ResponseModel<SalesResponse> getSalesById(Long salesId);

    ResponseModel<SalesResponse> saveSales(SalesRequest salesRequest);

    ResponseModel<SalesResponse> deleteSales(Long salesId);

    ResponseModel<SalesResponse> updateSales(Long salesId, SalesRequest salesRequest);
}

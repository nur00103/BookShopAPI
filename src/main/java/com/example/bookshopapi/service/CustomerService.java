package com.example.bookshopapi.service;

import com.example.bookshopapi.dto.request.CustomerRequest;
import com.example.bookshopapi.dto.response.CustomerResponse;
import com.example.bookshopapi.dto.response.ResponseModel;

import java.util.List;

public interface CustomerService {
    ResponseModel<List<CustomerResponse>> getAllCustomer();

    ResponseModel<CustomerResponse> getCustomerById(Long customerId);

    ResponseModel<CustomerResponse> addCustomer(CustomerRequest customerRequest);

    ResponseModel<CustomerResponse> updateCustomer(Long customerId, CustomerRequest customerRequest);

    ResponseModel<CustomerResponse> deleteCustomer(Long customerId);
}

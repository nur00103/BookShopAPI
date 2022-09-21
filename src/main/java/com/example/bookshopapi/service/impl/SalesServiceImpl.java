package com.example.bookshopapi.service.impl;

import com.example.bookshopapi.dto.request.SalesRequest;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.dto.response.SalesResponse;
import com.example.bookshopapi.entity.Book;
import com.example.bookshopapi.entity.Customer;
import com.example.bookshopapi.entity.Sales;
import com.example.bookshopapi.enums.ExceptionEnum;
import com.example.bookshopapi.exceptions.MyException;
import com.example.bookshopapi.repository.BookRepository;
import com.example.bookshopapi.repository.CustomerRepository;
import com.example.bookshopapi.repository.SalesRepository;
import com.example.bookshopapi.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {
    private final SalesRepository salesRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    @Override
    public ResponseModel<List<SalesResponse>> getAllSales() {
        List<Sales> salesList=salesRepository.findAll();
        List<SalesResponse> salesResponseList=salesList.stream().map(sales -> convertToSales(sales)).collect(Collectors.toList());
        return ResponseModel.<List<SalesResponse>>builder().result(salesResponseList).error(false)
                .code(ExceptionEnum.SUCCESS.getCode()).status(ExceptionEnum.SUCCESS.getMessage()).build();
    }

    @Override
    public ResponseModel<SalesResponse> getSalesById(Long salesId) {
        Sales sales=salesRepository.findById(salesId).get();
        if (sales==null){
            throw new MyException(ExceptionEnum.EMPTY_SALES);
        }
        SalesResponse salesResponse=convertToSales(sales);
        return ResponseModel.<SalesResponse>builder().result(salesResponse).error(false)
                .code(ExceptionEnum.SUCCESS.getCode()).status(ExceptionEnum.SUCCESS.getMessage()).build();
    }

    @Override
    public ResponseModel<SalesResponse> saveSales(SalesRequest salesRequest) {
        if (salesRequest==null){
            throw new MyException(ExceptionEnum.BAD_REQUEST);
        }
        Sales sales=new Sales();
        Customer customer=customerRepository.findById(salesRequest.getCustomer()).get();
        Book book=bookRepository.findById(salesRequest.getBook()).get();
        sales.setCustomer(customer);
        sales.setBook(book);
        sales.setQuantity(salesRequest.getQuantity());
        sales.setAmount(salesRequest.getAmount());
        Sales savedSales=salesRepository.save(sales);
        SalesResponse salesResponse=convertToSales(savedSales);
        return ResponseModel.<SalesResponse>builder().result(salesResponse).error(false)
                .code(ExceptionEnum.SUCCESS.getCode()).status(ExceptionEnum.SUCCESS.getMessage()).build();
    }

    @Override
    public ResponseModel<SalesResponse> deleteSales(Long salesId) {
        Sales sales=salesRepository.findById(salesId).get();
        if (sales==null){
            throw new MyException(ExceptionEnum.SALES_NOT_FOUND);
        }
        salesRepository.deleteById(salesId);
        return ResponseModel.<SalesResponse>builder().result(null).error(false)
                .code(ExceptionEnum.SUCCESS.getCode()).status(ExceptionEnum.SUCCESS.getMessage()).build();
    }

    @Override
    public ResponseModel<SalesResponse> updateSales(Long salesId, SalesRequest salesRequest) {
        Sales sales=salesRepository.findById(salesId).get();
        if (salesRequest==null){
            throw new MyException(ExceptionEnum.BAD_REQUEST);
        }
        if (sales==null){
            throw new MyException(ExceptionEnum.SALES_NOT_FOUND);
        }
        Customer customer=customerRepository.findById(salesRequest.getCustomer()).get();
        Book book=bookRepository.findById(salesRequest.getBook()).get();
        sales.setCustomer(customer);
        sales.setBook(book);
        sales.setQuantity(salesRequest.getQuantity());
        sales.setAmount(salesRequest.getAmount());
        Sales updatedSales=salesRepository.save(sales);
        SalesResponse salesResponse=convertToSales(updatedSales);
        return ResponseModel.<SalesResponse>builder().result(salesResponse).error(false)
                .code(ExceptionEnum.SUCCESS.getCode()).status(ExceptionEnum.SUCCESS.getMessage()).build();
    }

    public SalesResponse convertToSales(Sales sales){
        return SalesResponse.builder().customer(sales.getCustomer()).book(sales.getBook()).id(sales.getId())
                .quantity(sales.getQuantity()).amount(sales.getAmount()).salesDate(sales.getSalesDate()).build();
    }
}

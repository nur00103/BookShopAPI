package com.example.bookshopapi.service.impl;

import com.example.bookshopapi.dto.request.CustomerRequest;
import com.example.bookshopapi.dto.response.CustomerResponse;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.entity.Customer;
import com.example.bookshopapi.enums.ExceptionEnum;
import com.example.bookshopapi.exceptions.MyException;
import com.example.bookshopapi.repository.CustomerRepository;
import com.example.bookshopapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public ResponseModel<List<CustomerResponse>> getAllCustomer() {
        List<Customer>customerList=customerRepository.findAll();
        if (customerList.isEmpty() || customerList==null){
            throw new MyException(ExceptionEnum.EMPTY);
        }
        List<CustomerResponse> customerResponseList=customerList.stream().map(customer -> {
            CustomerResponse customerResponse=convertToResponse(customer);
            return customerResponse;
        }).collect(Collectors.toList());
        return ResponseModel.<List<CustomerResponse>>builder().result(customerResponseList)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    @Override
    public ResponseModel<CustomerResponse> getCustomerById(Long customerId) {
        CustomerResponse customerResponse=null;
        if (customerRepository.findById(customerId).isPresent()){
            Customer customer=customerRepository.findById(customerId).get();
            customerResponse=convertToResponse(customer);
        }else{
            throw new MyException(ExceptionEnum.USER_NOT_FOUND);
        }
        return ResponseModel.<CustomerResponse>builder().result(customerResponse).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    @Override
    public ResponseModel<CustomerResponse> addCustomer(CustomerRequest customerRequest) {
        if (customerRequest==null){
            throw new MyException(ExceptionEnum.BAD_REQUEST);
        }Customer customer=requestToEntity(customerRequest);
        Customer savedCustomer=customerRepository.save(customer);
        CustomerResponse customerResponse=convertToResponse(customer);
        return ResponseModel.<CustomerResponse>builder().result(customerResponse).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    @Override
    public ResponseModel<CustomerResponse> updateCustomer(Long customerId, CustomerRequest customerRequest) {
        if (customerRequest==null){
            throw new MyException(ExceptionEnum.BAD_REQUEST);
        }else if (!customerRepository.findById(customerId).isPresent()){
            throw new MyException(ExceptionEnum.USER_NOT_FOUND);
        }else {
            Customer customer=customerRepository.findById(customerId).get();
            customer=requestToEntity(customerRequest);
            customer.setId(customerId);
            Customer updatedCustomer=customerRepository.save(customer);
            CustomerResponse customerResponse=convertToResponse(updatedCustomer);
            return ResponseModel.<CustomerResponse>builder().result(customerResponse).error(false)
                    .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
        }
    }

    @Override
    public ResponseModel<CustomerResponse> deleteCustomer(Long customerId) {
        if (!customerRepository.findById(customerId).isPresent()){
            throw new MyException(ExceptionEnum.USER_NOT_FOUND);
        }else {
            customerRepository.deleteById(customerId);
        }
        return ResponseModel.<CustomerResponse>builder().result(null).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    public CustomerResponse convertToResponse(Customer customer){
        return CustomerResponse.builder().id(customer.getId()).name(customer.getName()).surname(customer.getSurname())
                .email(customer.getEmail()).address(customer.getAddress()).password(customer.getPassword())
                .phone(customer.getPhone()).username(customer.getUsername()).build();
    }

    public Customer requestToEntity(CustomerRequest customerRequest){
        Customer customer=new Customer();
        customer.setName(customerRequest.getName());
        customer.setSurname(customerRequest.getSurname());
        customer.setUsername(customerRequest.getUsername());
        customer.setPassword(customerRequest.getPassword());
        customer.setEmail(customerRequest.getEmail());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhone(customerRequest.getPhone());
        return customer;
    }
}

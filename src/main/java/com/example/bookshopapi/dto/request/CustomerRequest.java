package com.example.bookshopapi.dto.request;

import com.example.bookshopapi.entity.Customer;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CustomerRequest {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String surname;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String phone;
    @NotNull
    @NotBlank
    private String address;


}

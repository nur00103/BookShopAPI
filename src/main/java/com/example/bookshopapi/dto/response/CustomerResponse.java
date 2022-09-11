package com.example.bookshopapi.dto.response;

import com.example.bookshopapi.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CustomerResponse {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

}

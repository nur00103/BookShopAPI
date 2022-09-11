package com.example.bookshopapi.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseModel <T> {
    private T result;
    private String status;
    private boolean error;
    private int code;
}

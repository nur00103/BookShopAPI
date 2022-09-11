package com.example.bookshopapi.service;

import com.example.bookshopapi.dto.request.TypeRequest;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.dto.response.TypeResponse;

import java.util.List;

public interface TypeService {
    ResponseModel<List<TypeResponse>> getAllType();

    ResponseModel<TypeResponse> getTypeById(Long typeId);

    ResponseModel<TypeResponse> saveType(TypeRequest typeRequest);
}

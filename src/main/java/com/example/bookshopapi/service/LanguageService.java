package com.example.bookshopapi.service;

import com.example.bookshopapi.dto.request.LanguageRequest;
import com.example.bookshopapi.dto.response.LanguageResponse;
import com.example.bookshopapi.dto.response.ResponseModel;

import java.util.List;

public interface LanguageService {
    ResponseModel<List<LanguageResponse>> getAllLanguage();

    ResponseModel<LanguageResponse> getLanguageById(Long languageId);

    ResponseModel<LanguageResponse> saveLanguage(LanguageRequest languageRequest);
}

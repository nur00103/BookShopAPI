package com.example.bookshopapi.service;

import com.example.bookshopapi.dto.request.AuthorRequest;
import com.example.bookshopapi.dto.response.AuthorResponse;
import com.example.bookshopapi.dto.response.ResponseModel;

import java.util.List;

public interface AuthorService {
    ResponseModel<List<AuthorResponse>> getAllAuthor();

    ResponseModel<AuthorResponse> getAuthorById(Long authorId);

    ResponseModel<AuthorResponse> saveAuthor(AuthorRequest authorRequest);
}

package com.example.bookshopapi.service;

import com.example.bookshopapi.dto.request.BookRequest;
import com.example.bookshopapi.dto.response.BookResponse;
import com.example.bookshopapi.dto.response.ResponseModel;

import java.util.List;

public interface BookService {
    ResponseModel<List<BookResponse>> getAllBooks();

    ResponseModel<BookResponse> addBook(BookRequest bookRequest);

    ResponseModel<BookResponse> updateBook(Long bookId, BookRequest bookRequest);

    ResponseModel<BookResponse> getBookById(Long bookId);

    ResponseModel<BookResponse> deleteBook(Long bookId);
}

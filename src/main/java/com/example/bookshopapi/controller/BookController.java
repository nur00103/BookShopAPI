package com.example.bookshopapi.controller;

import com.example.bookshopapi.dto.request.BookRequest;
import com.example.bookshopapi.dto.response.BookResponse;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.entity.Book;
import com.example.bookshopapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseModel<List<BookResponse>> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{bookId}")
    public ResponseModel<BookResponse> getBookById(@PathVariable @Valid Long bookId){
        return bookService.getBookById(bookId);
    }
    @PostMapping("save")
    public ResponseModel<BookResponse> addBook(@RequestBody @Valid BookRequest bookRequest){
        return bookService.addBook(bookRequest);
    }
    @PutMapping("/{bookId}")
    public ResponseModel<BookResponse> updateBook(@PathVariable @Valid Long bookId,@RequestBody @Valid BookRequest bookRequest){
        return bookService.updateBook(bookId,bookRequest);
    }
    @DeleteMapping("/{bookId}")
    public ResponseModel<BookResponse> deleteBook(@PathVariable @Valid Long bookId){
        return bookService.deleteBook(bookId);
    }

}

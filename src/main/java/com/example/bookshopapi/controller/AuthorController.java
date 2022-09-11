package com.example.bookshopapi.controller;

import com.example.bookshopapi.dto.request.AuthorRequest;
import com.example.bookshopapi.dto.response.AuthorResponse;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseModel<List<AuthorResponse>> getAllAuthor(){
        return authorService.getAllAuthor();
    }
    @GetMapping("/{authorId}")
    public ResponseModel<AuthorResponse> getAuthorById(@PathVariable @Valid Long authorId){
        return authorService.getAuthorById(authorId);
    }
    @PostMapping("save")
    public ResponseModel<AuthorResponse> saveAuthor(@RequestBody @Valid AuthorRequest authorRequest){
        return authorService.saveAuthor(authorRequest);
    }

}

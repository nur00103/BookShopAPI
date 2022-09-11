package com.example.bookshopapi.service.impl;

import com.example.bookshopapi.dto.request.AuthorRequest;
import com.example.bookshopapi.dto.response.AuthorResponse;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.entity.Author;
import com.example.bookshopapi.enums.ExceptionEnum;
import com.example.bookshopapi.exceptions.MyException;
import com.example.bookshopapi.repository.AuthorRepository;
import com.example.bookshopapi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public ResponseModel<List<AuthorResponse>> getAllAuthor() {
        List<Author> authorList=authorRepository.findAll();
        if (authorList.isEmpty() || authorList==null){
            throw new MyException(ExceptionEnum.EMPTY_AUTHOR);
        }
        List<AuthorResponse> authorResponseList=authorList.stream().map(author -> convertToRes(author)).collect(Collectors.toList());
        return ResponseModel.<List<AuthorResponse>>builder().result(authorResponseList).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }


    @Override
    public ResponseModel<AuthorResponse> getAuthorById(Long authorId) {
        if (!authorRepository.findById(authorId).isPresent()){
            throw new MyException(ExceptionEnum.AUTHOR_NOT_FOUND);
        } Author author=authorRepository.findById(authorId).get();
        AuthorResponse authorResponse=convertToRes(author);
        return ResponseModel.<AuthorResponse>builder().result(authorResponse).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    @Override
    public ResponseModel<AuthorResponse> saveAuthor(AuthorRequest authorRequest) {
        Author author=authorRepository.findByAuthor(authorRequest.getAuthor());
        AuthorResponse authorResponse=null;
        if (authorRepository.findByAuthor(authorRequest.getAuthor())==null){
            Author author1=new Author();
            author1.setAuthor(authorRequest.getAuthor());
            Author savedAuthor=authorRepository.save(author1);
            authorResponse=convertToRes(savedAuthor);
        }else {
            authorResponse=convertToRes(author);
        }
        return ResponseModel.<AuthorResponse>builder().result(authorResponse).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    public AuthorResponse convertToRes(Author author){
        return AuthorResponse.builder().id(author.getId()).author(author.getAuthor()).build();
    }

}

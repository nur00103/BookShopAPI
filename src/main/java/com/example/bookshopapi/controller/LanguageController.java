package com.example.bookshopapi.controller;

import com.example.bookshopapi.dto.request.AuthorRequest;
import com.example.bookshopapi.dto.request.LanguageRequest;
import com.example.bookshopapi.dto.response.AuthorResponse;
import com.example.bookshopapi.dto.response.LanguageResponse;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping
    public ResponseModel<List<LanguageResponse>> getAllLanguage(){
        return languageService.getAllLanguage();
    }
    @GetMapping("/{languageId}")
    public ResponseModel<LanguageResponse> getLanguageById(@PathVariable @Valid Long languageId){
        return languageService.getLanguageById(languageId);
    }
    @PostMapping("save")
    public ResponseModel<LanguageResponse> saveLanguage(@RequestBody @Valid LanguageRequest languageRequest){
        return languageService.saveLanguage(languageRequest);
    }
}

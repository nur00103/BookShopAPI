package com.example.bookshopapi.controller;

import com.example.bookshopapi.dto.request.LanguageRequest;
import com.example.bookshopapi.dto.request.TypeRequest;
import com.example.bookshopapi.dto.response.LanguageResponse;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.dto.response.TypeResponse;
import com.example.bookshopapi.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("type")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @GetMapping
    public ResponseModel<List<TypeResponse>> getAllType(){
        return typeService.getAllType();
    }
    @GetMapping("/{typeId}")
    public ResponseModel<TypeResponse> getTypeById(@PathVariable @Valid Long typeId){
        return typeService.getTypeById(typeId);
    }
    @PostMapping("save")
    public ResponseModel<TypeResponse> saveType(@RequestBody @Valid TypeRequest typeRequest){
        return typeService.saveType(typeRequest);
    }
}

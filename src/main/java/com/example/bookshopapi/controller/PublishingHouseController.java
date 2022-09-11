package com.example.bookshopapi.controller;

import com.example.bookshopapi.dto.request.LanguageRequest;
import com.example.bookshopapi.dto.request.PublishingHouseRequest;
import com.example.bookshopapi.dto.response.LanguageResponse;
import com.example.bookshopapi.dto.response.PublishingHouseResponse;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.service.PublishingHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pubHouse")
@RequiredArgsConstructor
public class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;

    @GetMapping
    public ResponseModel<List<PublishingHouseResponse>> getAllPubHouse(){
        return publishingHouseService.getAllPubHouse();
    }
    @GetMapping("/{pubHouseId}")
    public ResponseModel<PublishingHouseResponse> getPubHouseById(@PathVariable @Valid Long pubHouseId){
        return publishingHouseService.getPubHouseById(pubHouseId);
    }
    @PostMapping("save")
    public ResponseModel<PublishingHouseResponse> savePubHouse(@RequestBody @Valid PublishingHouseRequest publishingHouseRequest){
        return publishingHouseService.savePubHouse(publishingHouseRequest);
    }
}

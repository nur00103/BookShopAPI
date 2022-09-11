package com.example.bookshopapi.service;

import com.example.bookshopapi.dto.request.PublishingHouseRequest;
import com.example.bookshopapi.dto.response.PublishingHouseResponse;
import com.example.bookshopapi.dto.response.ResponseModel;

import java.util.List;

public interface PublishingHouseService {
    ResponseModel<List<PublishingHouseResponse>> getAllPubHouse();

    ResponseModel<PublishingHouseResponse> getPubHouseById(Long pubHouseId);

    ResponseModel<PublishingHouseResponse> savePubHouse(PublishingHouseRequest publishingHouseRequest);
}

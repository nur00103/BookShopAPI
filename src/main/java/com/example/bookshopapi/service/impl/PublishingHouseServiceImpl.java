package com.example.bookshopapi.service.impl;

import com.example.bookshopapi.dto.request.LanguageRequest;
import com.example.bookshopapi.dto.request.PublishingHouseRequest;
import com.example.bookshopapi.dto.response.LanguageResponse;
import com.example.bookshopapi.dto.response.PublishingHouseResponse;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.entity.Language;
import com.example.bookshopapi.entity.PublishingHouse;
import com.example.bookshopapi.enums.ExceptionEnum;
import com.example.bookshopapi.exceptions.MyException;
import com.example.bookshopapi.repository.PublishingHouseRepository;
import com.example.bookshopapi.service.PublishingHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublishingHouseServiceImpl implements PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;

    @Override
    public ResponseModel<List<PublishingHouseResponse>> getAllPubHouse() {
        List<PublishingHouse> publishingHouseList=publishingHouseRepository.findAll();
        if (publishingHouseList.isEmpty() || publishingHouseList==null){
            throw new MyException(ExceptionEnum.UNKNOWN);
        }
        List<PublishingHouseResponse> publishingHouseResponseList=publishingHouseList.stream().map(publishingHouse -> convertToRes(publishingHouse)).collect(Collectors.toList());
        return ResponseModel.<List<PublishingHouseResponse>>builder().result(publishingHouseResponseList).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }


    @Override
    public ResponseModel<PublishingHouseResponse> getPubHouseById(Long pubHouseId) {
        if (!publishingHouseRepository.findById(pubHouseId).isPresent()){
            throw new MyException(ExceptionEnum.UNKNOWN);
        } PublishingHouse publishingHouse=publishingHouseRepository.findById(pubHouseId).get();
        PublishingHouseResponse publishingHouseResponse=convertToRes(publishingHouse);
        return ResponseModel.<PublishingHouseResponse>builder().result(publishingHouseResponse).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    @Override
    public ResponseModel<PublishingHouseResponse> savePubHouse(PublishingHouseRequest publishingHouseRequest) {
        PublishingHouse publishingHouse=publishingHouseRepository.findByHouseName(publishingHouseRequest.getPubHouse());
        PublishingHouseResponse publishingHouseResponse=null;
        if (publishingHouseRepository.findByHouseName(publishingHouseRequest.getPubHouse())==null){
            PublishingHouse publishingHouse1=new PublishingHouse();
            publishingHouse1.setHouseName(publishingHouseRequest.getPubHouse());
            PublishingHouse savedPubHouse=publishingHouseRepository.save(publishingHouse1);
            publishingHouseResponse=convertToRes(savedPubHouse);
        }else {
            publishingHouseResponse=convertToRes(publishingHouse);
        }
        return ResponseModel.<PublishingHouseResponse>builder().result(publishingHouseResponse).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    public PublishingHouseResponse convertToRes(PublishingHouse publishingHouse){
        return PublishingHouseResponse.builder().id(publishingHouse.getId()).pubHouse(publishingHouse.getHouseName()).build();
    }
}

package com.example.bookshopapi.service.impl;

import com.example.bookshopapi.dto.request.LanguageRequest;
import com.example.bookshopapi.dto.request.TypeRequest;
import com.example.bookshopapi.dto.response.LanguageResponse;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.dto.response.TypeResponse;
import com.example.bookshopapi.entity.Language;
import com.example.bookshopapi.entity.Type;
import com.example.bookshopapi.enums.ExceptionEnum;
import com.example.bookshopapi.exceptions.MyException;
import com.example.bookshopapi.repository.TypeRepository;
import com.example.bookshopapi.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    @Override
    public ResponseModel<List<TypeResponse>> getAllType() {
        List<Type> typeList=typeRepository.findAll();
        if (typeList.isEmpty() || typeList==null){
            throw new MyException(ExceptionEnum.UNKNOWN);
        }
        List<TypeResponse> typeResponseList=typeList.stream().map(type -> convertToRes(type)).collect(Collectors.toList());
        return ResponseModel.<List<TypeResponse>>builder().result(typeResponseList).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }


    @Override
    public ResponseModel<TypeResponse> getTypeById(Long typeId) {
        if (!typeRepository.findById(typeId).isPresent()){
            throw new MyException(ExceptionEnum.UNKNOWN);
        } Type type=typeRepository.findById(typeId).get();
        TypeResponse typeResponse=convertToRes(type);
        return ResponseModel.<TypeResponse>builder().result(typeResponse).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    @Override
    public ResponseModel<TypeResponse> saveType(TypeRequest typeRequest) {
        Type type=typeRepository.findByTypeName(typeRequest.getType());
        TypeResponse typeResponse=null;
        if (typeRepository.findByTypeName(typeRequest.getType())==null){
            Type type1=new Type();
            type1.setTypeName(typeRequest.getType());
            Type savedType=typeRepository.save(type1);
            typeResponse=convertToRes(savedType);
        }else {
            typeResponse=convertToRes(type);
        }
        return ResponseModel.<TypeResponse>builder().result(typeResponse).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    public TypeResponse convertToRes(Type type){
        return TypeResponse.builder().id(type.getId()).type(type.getTypeName()).build();
    }
}

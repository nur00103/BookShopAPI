package com.example.bookshopapi.service.impl;

import com.example.bookshopapi.dto.request.AuthorRequest;
import com.example.bookshopapi.dto.request.LanguageRequest;
import com.example.bookshopapi.dto.response.AuthorResponse;
import com.example.bookshopapi.dto.response.LanguageResponse;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.entity.Author;
import com.example.bookshopapi.entity.Language;
import com.example.bookshopapi.enums.ExceptionEnum;
import com.example.bookshopapi.exceptions.MyException;
import com.example.bookshopapi.repository.LanguageRepository;
import com.example.bookshopapi.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public ResponseModel<List<LanguageResponse>> getAllLanguage() {
        List<Language> languageList=languageRepository.findAll();
        if (languageList.isEmpty() || languageList==null){
            throw new MyException(ExceptionEnum.UNKNOWN);
        }
        List<LanguageResponse> languageResponseList=languageList.stream().map(language -> convertToRes(language)).collect(Collectors.toList());
        return ResponseModel.<List<LanguageResponse>>builder().result(languageResponseList).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }


    @Override
    public ResponseModel<LanguageResponse> getLanguageById(Long languageId) {
        if (!languageRepository.findById(languageId).isPresent()){
            throw new MyException(ExceptionEnum.UNKNOWN);
        } Language language=languageRepository.findById(languageId).get();
        LanguageResponse languageResponse=convertToRes(language);
        return ResponseModel.<LanguageResponse>builder().result(languageResponse).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    @Override
    public ResponseModel<LanguageResponse> saveLanguage(LanguageRequest languageRequest) {
        Language language=languageRepository.findByLanguage(languageRequest.getLanguage());
        LanguageResponse languageResponse=null;
        if (languageRepository.findByLanguage(languageRequest.getLanguage())==null){
            Language language1=new Language();
            language1.setLanguage(languageRequest.getLanguage());
            Language savedLanguage=languageRepository.save(language1);
            languageResponse=convertToRes(savedLanguage);
        }else {
            languageResponse=convertToRes(language);
        }
        return ResponseModel.<LanguageResponse>builder().result(languageResponse).error(false)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
    }

    public LanguageResponse convertToRes(Language language){
        return LanguageResponse.builder().id(language.getId()).language(language.getLanguage()).build();
    }

}

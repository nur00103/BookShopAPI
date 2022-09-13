package com.example.bookshopapi.controller;

import com.example.bookshopapi.dto.response.CustomerResponse;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.enums.ExceptionEnum;
import com.example.bookshopapi.exceptions.MyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MyException.class)
    public ResponseModel<CustomerResponse> expMy(MyException myException){
        return ResponseModel.<CustomerResponse>builder().status(myException.getMessage())
                .code(myException.getCode()).result(null).error(true).build();
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  ResponseModel<CustomerResponse> expReq(MethodArgumentTypeMismatchException e){
        return ResponseModel.<CustomerResponse>builder().status(ExceptionEnum.INPUT.getMessage())
                .code(ExceptionEnum.INPUT.getCode()).result(null).error(true).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel<CustomerResponse> exceptionInput(BindingResult bindingResult){
        final String[] error = {""};
        bindingResult.getFieldErrors()
                .stream()
                .forEach(fieldError -> {
                    error[0] = fieldError.getField()+" " + fieldError.getDefaultMessage() ;
                });
        return ResponseModel.<CustomerResponse>builder().status(error[0])
                .code(ExceptionEnum.BAD_REQUEST.getCode()).result(null).error(true).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseModel<CustomerResponse> exceptionAll(Exception e){
        return ResponseModel.<CustomerResponse>builder().status(e.getMessage())
                .code(404).result(null).error(true).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseModel<CustomerResponse> exceptionAllRunTime(RuntimeException e){
        return ResponseModel.<CustomerResponse>builder().status(e.getMessage())
                .code(404).result(null).error(true).build();
    }
}

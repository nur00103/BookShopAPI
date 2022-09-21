package com.example.bookshopapi.controller;

import com.example.bookshopapi.dto.request.SalesRequest;
import com.example.bookshopapi.dto.response.ResponseModel;
import com.example.bookshopapi.dto.response.SalesResponse;
import com.example.bookshopapi.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @GetMapping
    public ResponseModel<List<SalesResponse>> getAllSales(){
        return salesService.getAllSales();
    }
    @GetMapping("/{salesId}")
    public ResponseModel<SalesResponse> getSalesById(@PathVariable @Valid Long salesId){
        return salesService.getSalesById(salesId);
    }
    @PostMapping("save")
    public ResponseModel<SalesResponse> saveSales(@Valid @RequestBody SalesRequest salesRequest) {
        return salesService.saveSales(salesRequest);
    }
    @DeleteMapping("/{salesId}")
    public ResponseModel<SalesResponse> deleteSales(@PathVariable @Valid Long salesId){
        return salesService.deleteSales(salesId);
    }
    @PutMapping("/{salesId}")
    public ResponseModel<SalesResponse> updateSales(@Valid @RequestBody SalesRequest salesRequest,@Valid @PathVariable Long salesId){
        return salesService.updateSales(salesId,salesRequest);
    }
}

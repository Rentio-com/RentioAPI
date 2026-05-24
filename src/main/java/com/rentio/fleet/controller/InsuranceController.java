package com.rentio.fleet.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentio.fleet.dto.insurance.UpdateInsuranceRequest;
import com.rentio.fleet.interfaces.common.InsuranceView;
import com.rentio.fleet.service.InsuranceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @GetMapping("/{id}")
    public InsuranceView getInsuranceById(
        @PathVariable UUID id
    ) {
        return insuranceService.getInsuranceById(id);
    }

    @PatchMapping("/{id}")
    public InsuranceView updateInsurance(
        @PathVariable UUID id, 
        @Valid @RequestBody UpdateInsuranceRequest body
    ) {
        return insuranceService.update(id, body);
    }

    @DeleteMapping("/{id}")
    public void deleteInsurance(@PathVariable UUID id){
        insuranceService.delete(id);
    }
    
    
}

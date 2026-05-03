package com.rentio.fleet.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RestController;

import com.rentio.common.dto.PaginationAndSortResponse;
import com.rentio.fleet.dto.insurance.CreateInsuranceRequest;
import com.rentio.fleet.interfaces.common.InsuranceView;
import com.rentio.fleet.interfaces.common.VehicleView;
import com.rentio.fleet.service.InsuranceService;
import com.rentio.fleet.service.VehicleService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private InsuranceService insuranceService;

    // --- Vehicle ---
    @GetMapping
    @ResponseBody
    public PaginationAndSortResponse<?> getVehicles(@PageableDefault(page = 0, size = 20, sort = "make", direction = Sort.Direction.DESC) Pageable pagable) {
        return PaginationAndSortResponse.from(vehicleService.listVehicles(pagable));
    }

    @GetMapping("/{id}")
    public VehicleView getVehicleById(@PathVariable UUID id) {
        return vehicleService.getVehicleById(id);
    }

    // --- Insurance ---
    @GetMapping("/{id}/insurances")
    public PaginationAndSortResponse<?> getVehicleInsurances(
        @PageableDefault(page = 0, size = 10, sort = "active", direction = Sort.Direction.DESC) Pageable pageable,
        @PathVariable UUID id
    ) {
        return PaginationAndSortResponse.from(insuranceService.listInsuranceByVehicleId(id, pageable));
    }

    @PostMapping("/{id}/insurance")
    public InsuranceView createInsurance(
        @PathVariable UUID id,
        @Valid @RequestBody CreateInsuranceRequest request
    ) {
        return insuranceService.create(id, request);
    }
    
}

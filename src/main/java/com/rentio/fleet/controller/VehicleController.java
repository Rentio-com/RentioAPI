package com.rentio.fleet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RestController;

import com.rentio.common.dto.PaginationAndSortResponse;
import com.rentio.fleet.service.VehicleService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


    @GetMapping("/vehicles")
    @ResponseBody
    public PaginationAndSortResponse<?> getVehicles(@PageableDefault(page = 0, size = 20, sort = "make", direction = Sort.Direction.DESC) Pageable pagable) {
        return PaginationAndSortResponse.from(vehicleService.listVehicles(pagable));
    }
    
}

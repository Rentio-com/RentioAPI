package com.rentio.customer.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rentio.common.dto.PaginationAndSortResponse;
import com.rentio.customer.service.CustomerService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public PaginationAndSortResponse<?> listCustomers(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        return PaginationAndSortResponse.from(customerService.listCustomers(pageable));
    }

    @GetMapping("/customer/{id}/rentals")
    public PaginationAndSortResponse<?> getCustomerRentals(@PathVariable UUID id, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        return PaginationAndSortResponse.from(customerService.getCustomerRentals(id, pageable));
    }
    
    
}

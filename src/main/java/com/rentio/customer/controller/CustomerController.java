package com.rentio.customer.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rentio.common.dto.PaginationAndSortResponse;
import com.rentio.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
public class CustomerController {
    
    private final CustomerService customerService;

    @GetMapping("/customers")
    public PaginationAndSortResponse<?> listCustomers(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        return PaginationAndSortResponse.from(customerService.listCustomers(pageable));
    }    
    
}

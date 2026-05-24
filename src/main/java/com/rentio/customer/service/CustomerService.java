package com.rentio.customer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rentio.customer.dto.response.DefaultCustomerResponse;
import com.rentio.customer.model.Customer;
import com.rentio.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Page<?> listCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);

        return customers.map(customer -> DefaultCustomerResponse.from(customer));
    }
}

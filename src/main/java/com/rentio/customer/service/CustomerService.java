package com.rentio.customer.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rentio.customer.dto.response.DefaultCustomerResponse;
import com.rentio.customer.model.Customer;
import com.rentio.customer.repository.CustomerRepository;
import com.rentio.rental.dto.response.DefaultRentalResponse;
import com.rentio.rental.repository.RentalRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public Page<?> listCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);

        return customers.map(customer -> DefaultCustomerResponse.from(customer));
    }

    public Page<?> getCustomerRentals(UUID customerId, Pageable pageable) {
        customerRepository.findById(customerId).orElseThrow();

        return rentalRepository.findByCustomerId(customerId, pageable).map(rental -> DefaultRentalResponse.from(rental));
    }
}

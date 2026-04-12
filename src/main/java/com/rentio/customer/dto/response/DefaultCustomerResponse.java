package com.rentio.customer.dto.response;

import com.rentio.customer.model.Customer;

public record DefaultCustomerResponse(Customer customer) {
    
    public static DefaultCustomerResponse from(Customer customer) {
        return new DefaultCustomerResponse(customer);
    }
}

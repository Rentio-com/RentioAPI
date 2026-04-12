package com.rentio.customer.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rentio.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    
}

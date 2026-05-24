package com.rentio.rental.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rentio.rental.dto.response.DefaultRentalResponse;
import com.rentio.rental.model.Rental;
import com.rentio.rental.repository.RentalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {
    
    private final RentalRepository rentalRepository;

    public Page<DefaultRentalResponse> listRentals(Pageable pageable) {
        Page<Rental> rentals = rentalRepository.findAll(pageable);

        return rentals.map(rental -> DefaultRentalResponse.from(rental));
    }

    public Object getRentalById(UUID rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow();

        return DefaultRentalResponse.from(rental);
    }

    public Page<DefaultRentalResponse> findByCustomerId(Pageable page, UUID customerId){
        Page<Rental> rentals = rentalRepository.findByCustomerId(customerId, page);

        return rentals.map(rental -> DefaultRentalResponse.from(rental));
    }
}
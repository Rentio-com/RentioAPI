package com.rentio.rental.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentio.rental.model.Rental;


@Repository
public interface RentalRepository extends JpaRepository<Rental, UUID> {
    Page<Rental> findByCustomerId(UUID customerId, Pageable pageable);
    Page<Rental> findByVehicleId(UUID vehicleId, Pageable pageable);
}

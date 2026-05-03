package com.rentio.fleet.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rentio.fleet.model.VehicleInsurance;


public interface InsuranceRepository extends JpaRepository<VehicleInsurance, UUID> {
    Page<VehicleInsurance> findByVehicleId(UUID vehicleId, Pageable pageable);
}

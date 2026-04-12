package com.rentio.fleet.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rentio.fleet.dto.response.VehicleDefaultResponse;
import com.rentio.fleet.model.Vehicle;
import com.rentio.fleet.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Page<?> listVehicles(Pageable pageable) {
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);

        return vehiclePage.map(vehicle -> VehicleDefaultResponse.from(vehicle));
    }
}

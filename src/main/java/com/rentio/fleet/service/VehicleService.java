package com.rentio.fleet.service;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rentio.fleet.dto.response.VehicleDefaultResponse;
import com.rentio.fleet.interfaces.common.VehicleView;
import com.rentio.fleet.model.Vehicle;
import com.rentio.fleet.repository.VehicleRepository;
import com.rentio.note.service.NoteService;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private NoteService noteService;

    public Page<?> listVehicles(Pageable pageable) {
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);

        return vehiclePage.map(vehicle -> VehicleDefaultResponse.from(vehicle));
    }

    public VehicleView getVehicleById(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow();

        return VehicleDefaultResponse.from(vehicle);
    }

    public void verifyExists(UUID vehicleId) {
        vehicleRepository.findById(vehicleId).orElseThrow();
    }

}

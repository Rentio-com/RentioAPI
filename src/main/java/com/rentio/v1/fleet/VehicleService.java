package com.rentio.v1.fleet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentio.v1.fleet.dto.VehiclePublicDTO;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehiclePublicDTO> getAllCars() {
       List<Vehicle> cars = (List<Vehicle>) vehicleRepository.findAll();

    //    return cars.stream().map()
    }
}

package com.rentio.fleet.dto.response;

import java.util.UUID;

import com.rentio.fleet.enums.Status;
import com.rentio.fleet.model.Vehicle;

public record VehiclePublicResponse(UUID id, String make, String model, int year, String engineInfo, boolean available) {

    public static VehiclePublicResponse from (Vehicle vehicle) {
        return new VehiclePublicResponse(
            vehicle.getId(),
            vehicle.getMake(),
            vehicle.getModel(), 
            vehicle.getProductionYear(), 
            vehicle.getEngineInfo(), 
            vehicle.getStatus() == Status.AVAILABLE || vehicle.getStatus() == Status.READY_TO_RENT);
    }
    
}


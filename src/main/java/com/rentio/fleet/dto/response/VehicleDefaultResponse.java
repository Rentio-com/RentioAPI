package com.rentio.fleet.dto.response;

import com.rentio.fleet.model.Vehicle;

public record VehicleDefaultResponse(Vehicle vehicle) {
    
    public static VehicleDefaultResponse from(Vehicle vehicle){
        return new VehicleDefaultResponse(vehicle);
    }
    
}

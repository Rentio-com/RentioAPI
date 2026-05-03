package com.rentio.fleet.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.rentio.fleet.interfaces.common.VehicleView;
import com.rentio.fleet.model.Vehicle;

public record VehicleDefaultResponse(@JsonUnwrapped Vehicle vehicle) implements VehicleView {
    
    public static VehicleDefaultResponse from( Vehicle vehicle){
        return new VehicleDefaultResponse(vehicle);
    }
    
}

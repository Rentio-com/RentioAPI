package com.rentio.v1.fleet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentio.v1.fleet.dto.VehiclePublicDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


    @GetMapping("/")
    @ResponseBody
    public List<VehiclePublicDTO> getVehicles() {
        return vehicleService.getAllCars();
    }
    
}

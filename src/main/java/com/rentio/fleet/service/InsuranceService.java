package com.rentio.fleet.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rentio.common.exception.BussinesException;
import com.rentio.common.security.CurrentUserProvider;
import com.rentio.common.validation.ValidationError;
import com.rentio.fleet.dto.insurance.CreateInsuranceRequest;
import com.rentio.fleet.dto.insurance.UpdateInsuranceRequest;
import com.rentio.fleet.interfaces.common.InsuranceView;
import com.rentio.fleet.mapper.insurance.InsuranceMapper;
import com.rentio.fleet.model.VehicleInsurance;
import com.rentio.fleet.repository.InsuranceRepository;

@Service
public class InsuranceService {
    
    @Autowired InsuranceRepository insuranceRepository;
    @Autowired VehicleService vehicleService;
    @Autowired CurrentUserProvider currentUserProvider;
    @Autowired InsuranceMapper insuranceMapper;

    public Page<?> listInsuranceByVehicleId(UUID vehicleId, Pageable pageable){
        Page<VehicleInsurance> insurances = insuranceRepository.findByVehicleId(vehicleId, pageable);

        return insurances.map(insurance -> insuranceMapper.toResponse(insurance));
    }

    public InsuranceView getInsuranceById(UUID insuranceId) {
        VehicleInsurance insurance = insuranceRepository.findById(insuranceId).orElseThrow();

        return insuranceMapper.toResponse(insurance);
    }

    public InsuranceView create(UUID vehicleId, CreateInsuranceRequest request){
       vehicleService.verifyExists(vehicleId);

       VehicleInsurance insurance = insuranceMapper.toEntity(request);
       insurance.setCompanyId(currentUserProvider.getCompanyId());
       insurance.setCreatedBy(currentUserProvider.getUserId());
       insurance.setVehicleId(vehicleId);

        validateBusinessRules(insurance);


       VehicleInsurance saved = insuranceRepository.save(insurance);

       return insuranceMapper.toResponse(saved);
    } 

    public InsuranceView update(UUID id, UpdateInsuranceRequest request) {
        VehicleInsurance insurance = insuranceRepository.findById(id).orElseThrow();

        insuranceMapper.updateEntity(request, insurance);
        validateBusinessRules(insurance);

        VehicleInsurance saved = insuranceRepository.save(insurance);

        return insuranceMapper.toResponse(saved);
    }

    public void delete(UUID id) {
        VehicleInsurance insurance = insuranceRepository.findById(id).orElseThrow();

        insuranceRepository.delete(insurance);
    }

    
    private void validateBusinessRules(VehicleInsurance insurance) {
        LocalDate endDate = insurance.getEndDate();
        LocalDate starDate = insurance.getStartDate();

        if(!endDate.isAfter(starDate)) {
            throw new BussinesException(ValidationError.INVALID_DATE_RANGE, "endDate");
        }        
    }
}

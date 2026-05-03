package com.rentio.fleet.mapper.insurance;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.rentio.fleet.dto.insurance.CreateInsuranceRequest;
import com.rentio.fleet.dto.insurance.InsuranceDefaultResponse;
import com.rentio.fleet.dto.insurance.UpdateInsuranceRequest;
import com.rentio.fleet.model.VehicleInsurance;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface InsuranceMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "vehicleId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    VehicleInsurance toEntity(CreateInsuranceRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "vehicleId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(UpdateInsuranceRequest request, @MappingTarget VehicleInsurance entity);

    InsuranceDefaultResponse toResponse(VehicleInsurance vehicleInsurance);
}

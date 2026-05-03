package com.rentio.fleet.dto.insurance;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.rentio.common.validation.ValidationError;
import com.rentio.fleet.enums.InsurancePaymentFrequency;
import com.rentio.fleet.enums.InsurancePolicyType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateInsuranceRequest( 
    @NotNull(message = ValidationError.REQUIRED)
    InsurancePolicyType insurancePolicyType,

    @Size(max = 254, message = ValidationError.TOO_LONG)
    String insurerName,

    @NotEmpty(message = ValidationError.REQUIRED)
    @NotBlank(message = ValidationError.REQUIRED)
    @Size(max = 254, message = ValidationError.TOO_LONG)
    String policyNumber,

    @NotNull(message = ValidationError.REQUIRED)
    LocalDate startDate,

    @NotNull(message = ValidationError.REQUIRED)
    LocalDate endDate,

    @Positive(message = ValidationError.MUST_BE_POSITIVE)
    @NotNull(message = ValidationError.REQUIRED)
    @Max(value = 100000, message = ValidationError.INVALID_FORMAT)
    BigDecimal premiumAmount,

    @NotNull(message = ValidationError.REQUIRED)
    InsurancePaymentFrequency insurancePaymentFrequency,

    @NotNull(message = ValidationError.REQUIRED)
    Boolean active
) {
    public CreateInsuranceRequest {
        if(active == null) active = true;
    }
}

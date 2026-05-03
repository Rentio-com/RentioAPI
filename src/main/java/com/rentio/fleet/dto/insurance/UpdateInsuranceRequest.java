package com.rentio.fleet.dto.insurance;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.rentio.common.validation.ValidationError;
import com.rentio.fleet.enums.InsurancePaymentFrequency;
import com.rentio.fleet.enums.InsurancePolicyType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateInsuranceRequest(
    InsurancePolicyType insurancePolicyType,

    @Size(max = 254, message = ValidationError.TOO_LONG)
    String insurerName,

    @Size(max = 254, message = ValidationError.TOO_LONG)
    String policyNumber,

    LocalDate startDate,

    LocalDate endDate,

    @Positive(message = ValidationError.MUST_BE_POSITIVE)
    @Max(value = 100000, message = ValidationError.INVALID_FORMAT)
    BigDecimal premiumAmount,

    InsurancePaymentFrequency insurancePaymentFrequency,

    Boolean active
) {}

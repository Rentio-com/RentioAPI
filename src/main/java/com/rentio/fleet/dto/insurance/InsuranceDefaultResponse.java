package com.rentio.fleet.dto.insurance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.rentio.fleet.enums.InsurancePaymentFrequency;
import com.rentio.fleet.enums.InsurancePolicyType;
import com.rentio.fleet.interfaces.common.InsuranceView;

public record InsuranceDefaultResponse(
    UUID id,
    UUID vehicleId,
    UUID companyId,
    UUID createdBy,
    InsurancePolicyType policyType,
    String insurerName,
    String policyNumber,
    LocalDate startDate,
    LocalDate endDate,
    BigDecimal premiumAmount,
    InsurancePaymentFrequency paymentFrequency,
    boolean active
) implements InsuranceView {
}

package com.rentio.fleet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.rentio.common.model.BaseEntity;
import com.rentio.fleet.enums.InsurancePaymentFrequency;
import com.rentio.fleet.enums.InsurancePolicyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "vehicle_insurances")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class VehicleInsurance extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private UUID id;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "vehicle_id", nullable = false)
    private UUID vehicleId;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "policy_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private InsurancePolicyType insurancePolicyType;

    @Column(name = "insurer_name", nullable = true)
    private String insurerName;

    @Column(name = "policy_number", nullable = false)
    @NotBlank
    private String policyNumber;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "premium_amount", nullable = false)
    private BigDecimal premiumAmount;

    @Column(name = "payment_frequency", nullable = false)
    @Enumerated(EnumType.STRING)
    private InsurancePaymentFrequency insurancePaymentFrequency;

    @Column(name = "active", nullable = false)
    private boolean active;

    // --- Relationships ---
    // Parent -> Vehicle
    // Child -> Note(EntityType = VEHICLE_INSURANCE)
    // API -> /api/v1/vehicles/{vehicleId}/insurance
}

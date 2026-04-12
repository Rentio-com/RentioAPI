package com.rentio.customer.model;

import java.time.LocalDate;
import java.util.UUID;

import com.rentio.common.model.BaseEntity;
import com.rentio.customer.enums.CustomerType;
import com.rentio.customer.enums.PersonalIdType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Customer extends BaseEntity {
    
    // --- Identity ---
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private UUID id;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    // --- Basic Info ---
    @Column(name = "first_name", nullable = false)
    @NonNull
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NonNull
    @NotBlank
    private String lastName;

    // --- Contact --- 
    @Column(name = "phone_number", nullable = false)
    @NotBlank
    @NotNull
    private String phoneNumber;

    @Column(name = "email", nullable = true)
    private String email;

    // --- Identity documents ---
    @Column(name = "personal_id_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonalIdType personalIdType;

    @Column(name = "personal_id_number", nullable = true)
    private String personalIdNumber;

    @Column(name = "document_number", nullable = false)
    @NotBlank
    @NonNull
    private String documentNumber;

    @Column(name = "document_expiry", nullable = true)
    private LocalDate documentExpiry;

    @Column(name = "document_country", nullable = false)
    @NonNull
    @NotBlank
    private String documentCountry;

    // --- Driving license ---
    @Column(name = "driver_license_number", nullable = false)
    @NonNull
    @NotBlank
    private String driverLicenseNumber;

    @Column(name = "driver_license_expiry", nullable = true)
    private LocalDate driverLicenseExpiry;

    @Column(name = "driver_license_no_expiry", nullable = false)
    private boolean driverLicenseNoExpiry = false;

    @Column(name = "driver_license_country", nullable = false)
    @NonNull
    @NotBlank
    private String driverLicenseCountry;

    // --- Customer info ---
    @Column(name = "customer_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @Column(name = "company_name", nullable = true)
    private String companyName;

    @Column(name = "tax_id", nullable = true)
    private String taxId;

    // --- Internal ---
    @Column(name = "blacklisted", nullable = false)
    private boolean blackListed = false;

    @Column(name = "blacklisted_reason", nullable = true)
    private String blacklistedReason;

    @Column(name = "notes", nullable = true)
    private String notes;
}

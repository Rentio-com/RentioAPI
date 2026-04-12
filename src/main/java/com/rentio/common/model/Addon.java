package com.rentio.common.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.rentio.common.enums.AddonPricingType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "addons")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Addon extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "name", nullable = false)
    @NonNull
    @NotBlank
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "pricing_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AddonPricingType pricingType;

    @Column(name = "price", nullable = false)
    @PositiveOrZero
    private BigDecimal price;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true; 
}

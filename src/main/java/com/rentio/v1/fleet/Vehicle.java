package com.rentio.v1.fleet;

import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.rentio.v1.fleet.enums.EngineType;
import com.rentio.v1.fleet.enums.Transmission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    // Rental companies relation
    @Column(name = "rental_id", nullable = false)
    private Long rental_id;

    @Column(name = "make", nullable = false)
    @NonNull
    @NotBlank
    private String make;

    @Column(name = "model", nullable = false)
    @NonNull
    @NotBlank
    private String model;

    @Column(name = "license_plate", nullable = false, unique = true)
    @NonNull
    @NotBlank
    private String licensePlate;

    @Column(name = "engine_info", nullable = false)
    @NonNull
    @NotBlank
    private String engineInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type", nullable = false)
    @NonNull
    private EngineType engineType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission", nullable = false)
    @NonNull
    private Transmission transmission;

    @Column(name = "fuel_level", nullable = false)
    private double fuelLevel;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "features", nullable = true, columnDefinition = "jsonb")
    private Map<String, Object> features;

    // Vehicle Documents relation

    // Vehicle Events relation

    // Pricing Strategies relation
}

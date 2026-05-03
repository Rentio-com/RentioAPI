package com.rentio.fleet.model;

import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.rentio.common.enums.VehicleSegment;
import com.rentio.common.enums.VehicleType;
import com.rentio.common.model.BaseEntity;
import com.rentio.fleet.enums.EngineType;
import com.rentio.fleet.enums.Transmission;
import com.rentio.fleet.enums.Status;

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
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vehicle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "make", nullable = false)
    @NonNull
    @NotBlank
    private String make;

    @Column(name = "model", nullable = false)
    @NonNull
    @NotBlank
    private String model;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Column(name = "segment", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleSegment segment;

    @Column(name = "license_plate", nullable = false, unique = true)
    @NonNull
    @NotBlank
    private String licensePlate;

    @Column(name = "vin", nullable = false, unique = true)
    @NonNull
    @NotBlank
    private String vin;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @NonNull
    @NotBlank 
    private Status status;

    @Column(name = "production_year", nullable = true)
    private int productionYear;

    @Column(name = "engine_info", nullable = true)
    private String engineInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type", nullable = false)
    private EngineType engineType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission", nullable = false)
    private Transmission transmission;

    @Column(name = "color", nullable = true)
    private String color;

    @Column(name = "fuel_level", nullable = false)
    private int fuelLevel;

    @Column(name = "mileage", nullable = false)
    private int mileage;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "features", nullable = true, columnDefinition = "jsonb")
    private Map<String, Object> features;

    // --- Relationships ---
    // Child -> Insurance
    // Child -> Notes(EntityType = VEHICLE)

    // Vehicle Documents relation

    // Vehicle Events relation

    // Pricing Strategies relation

    // Vehicle type relation

    // Related entities - query via:
    // rentalRepository.findByVechicleId(id)
    // vehicleNoteRepository.finyByVehicleId(id)
}

package com.rentio.rental.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.rentio.common.model.BaseEntity;
import com.rentio.rental.enums.RentalStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "rentals")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Rental extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // --- Identity ---
    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    // --- Employee ---
    @Column(name = "pickup_employee_id", nullable = true)
    private UUID pickupEmployeeId;

    @Column(name = "return_employee_id", nullable = true )
    private UUID returnEmployeeId;

    // --- Vehicle ---
    @Column(name = "vehicle_id", nullable = false)
    private UUID vehicleId;

    @Column(name = "vehicle_type", nullable = false)
    @NonNull
    @NotBlank
    private String vehicleType;

    @Column(name = "vehicle_segment", nullable = false)
    @NonNull
    @NotBlank
    private String vehicleSegment;

    // --- Dates ---
    @Column(name = "scheduled_pickup", nullable = false)
    @NonNull
    private LocalDateTime scheduledPickup;

    @Column(name = "scheduled_return", nullable = false)
    @NonNull
    private LocalDateTime scheduledReturn;

    @Column(name = "actual_pickup", nullable = true)
    private LocalDateTime actualPickup;

    @Column(name = "actual_return", nullable = true)
    private LocalDateTime actualReturn;

    // --- Pricing ---
    @Column(name = "daily_rate", nullable = false)
    @Positive
    private BigDecimal dailyRate;

    @Column(name = "estimated_total", nullable = false)
    @Positive
    private BigDecimal estimatedTotal;

    @Column(name = "final_total", nullable = true)
    @Positive
    private BigDecimal finalTotal;

    // --- Pickup location ---
    @Column(name = "pickup_address", nullable = false)
    @NonNull
    @NotBlank
    private String pickupAddress;

    @Column(name = "pickup_city", nullable = false)
    @NonNull
    @NotBlank
    private String pickupCity;

    @Column(name = "pickup_lat", nullable = true)
    private Double pickupLat;

    @Column(name = "pickup_lng", nullable = true)
    private Double pickupLng;

    @Column(name = "pickup_transport_number", nullable = true)
    private String pickupTransportNumber;

    @Column(name = "pickup_notes", nullable = true)
    private String pickupNotes;

     // --- return location ---
    @Column(name = "return_address", nullable = false)
    @NonNull
    @NotBlank
    private String returnAddress;

    @Column(name = "return_city", nullable = false)
    @NonNull
    @NotBlank
    private String returnCity;

    @Column(name = "return_lat", nullable = true)
    private Double returnLat;

    @Column(name = "return_lng", nullable = true)
    private Double returnLng;

    @Column(name = "return_notes", nullable = true)
    private String returnNotes;

    // --- Status ---
    @Column(name = "rental_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    // // --- Notes ---
    // @Column(name = "staff_notes", nullable = true)
    // private String staffNotes;

    // @Column(name = "customer_notes", nullable = true)
    // private String customerNotes;
}

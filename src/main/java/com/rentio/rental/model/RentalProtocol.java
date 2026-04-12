package com.rentio.rental.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.rentio.rental.enums.CleanlinessLevel;
import com.rentio.rental.enums.DamageLevel;
import com.rentio.rental.enums.InspectionConfidence;
import com.rentio.rental.enums.ProtocolType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rental_protocols")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RentalProtocol {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "rental_id", nullable = false)
    private UUID rentalId;

    @Column(name = "employee_id", nullable = false)
    private UUID employeeId;

    @Column(name = "customer_signature", nullable = true) // nullable needs to be changed
    private String customerSignature; // Needs to be implemented later

    @Column(name = "mileage", nullable = false)
    @Positive
    private int mileage;

    @Column(name = "fuel_level", nullable = false)
    @PositiveOrZero
    private int fuelLevel;

    @Column(name = "protocol_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProtocolType protocolType;

    // Exterior
    @Column(name = "exterior_damage_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private DamageLevel exteriorDamageLevel;

    @Column(name = "exterior_cleanliness_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private CleanlinessLevel exteriorCleanlinessLevel;

    @Column(name = "exterior_conifidence", nullable = false)
    @Enumerated(EnumType.STRING)
    private InspectionConfidence exteriorConfidence;

    // Interior
    @Column(name = "interior_damage_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private DamageLevel interiorDamageLevel;

    @Column(name = "interior_cleanliness_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private CleanlinessLevel interiorCleanlinessLevel;

    @Column(name = "interior_conifidence", nullable = false)
    @Enumerated(EnumType.STRING)
    private InspectionConfidence interiorConfidence;

    @Column(name = "conditions_note", nullable = true)
    private String conditionsNote;

    @Column(name = "notes", nullable = true)
    private String notes;

    @Column(name = "photos", columnDefinition = "jsonb", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> photos;

    @Column(name = "damage_marks", columnDefinition = "jsonb", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> damageMarks;
}

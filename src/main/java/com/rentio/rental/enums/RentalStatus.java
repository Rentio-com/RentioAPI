package com.rentio.rental.enums;

public enum RentalStatus {

    // === Pre-rental ===
    CREATED,
    CONFIRMED,
    SCHEDULED,
    CAR_READY,

    // === Pickup phase ===
    IN_DELIVERY,
    PENDING_PICKUP,
    INSPECTION_ONGOING,

    // === Active rental ===
    ACTIVE,
    OVERDUE,
    EXTENDED,

    // === Return Phase ===
    RETURN_SCHEDULED,
    PENDING_RETURN,
    RETURN_INSPECTION_ONGOING,

    // === Closed ===
    COMPLETED,
    CANCELED,

    // === Corrupted states ===
    ON_HOLD, // Issue needs resolution
    DISPUTED // Car damaged
}

package com.rentio.rental.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.rentio.rental.model.Rental;

public record DefaultRentalResponse(@JsonUnwrapped Rental rental) {

    public static DefaultRentalResponse from(Rental rental) {
        return new DefaultRentalResponse(rental);
    }

}

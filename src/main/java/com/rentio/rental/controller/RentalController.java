package com.rentio.rental.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rentio.common.dto.PaginationAndSortResponse;
import com.rentio.note.enums.NoteEntityType;
import com.rentio.rental.service.RentalService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;
    
    @GetMapping("/rentals")
    public PaginationAndSortResponse<?> listRentals(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        return PaginationAndSortResponse.from(rentalService.listRentals(PageRequest.of(page, size)));
    }

    @GetMapping("/rental/{id}")
    public Object getRentalById(@PathVariable UUID id) {
        return rentalService.getRentalById(id);
    }
    
    @GetMapping("/rental/{id}/notes")
    public PaginationAndSortResponse<?> getRentalNotes(
        @PathVariable UUID id,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
       return PaginationAndSortResponse.from(rentalService.getRentalNotes(PageRequest.of(page, size), id, NoteEntityType.RENTAL));
    }
    
    
}

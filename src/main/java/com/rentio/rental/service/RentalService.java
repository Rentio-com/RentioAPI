package com.rentio.rental.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rentio.note.dto.response.DefaultNoteResponse;
import com.rentio.note.enums.NoteEntityType;
import com.rentio.note.model.Note;
import com.rentio.note.repository.NoteRepository;
import com.rentio.rental.dto.response.DefaultRentalResponse;
import com.rentio.rental.model.Rental;
import com.rentio.rental.repository.RentalRepository;

@Service
public class RentalService {
    
    @Autowired 
    private RentalRepository rentalRepository;

    @Autowired NoteRepository noteRepository;

    public Page<DefaultRentalResponse> listRentals(Pageable pageable) {
        Page<Rental> rentals = rentalRepository.findAll(pageable);

        return rentals.map(rental -> DefaultRentalResponse.from(rental));
    }

    public Object getRentalById(UUID rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow();

        return DefaultRentalResponse.from(rental);
    }

    public Page<DefaultRentalResponse> findByCustomerId(Pageable page, UUID customerId){
        Page<Rental> rentals = rentalRepository.findByCustomerId(customerId, page);

        return rentals.map(rental -> DefaultRentalResponse.from(rental));
    }

    public Page<DefaultNoteResponse> getRentalNotes(Pageable page, UUID rentalId, NoteEntityType entityType) {
        Page<Note> notes = noteRepository.findByEntityTypeAndEntityId(entityType, rentalId, page);

        return notes.map(note -> DefaultNoteResponse.from(note));
    }
}
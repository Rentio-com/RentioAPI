package com.rentio.note.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.rentio.note.model.Note;

public record DefaultNoteResponse(@JsonUnwrapped Note note) {
    
    public static DefaultNoteResponse from(Note note) {
        return new DefaultNoteResponse(note);
    }
    
}

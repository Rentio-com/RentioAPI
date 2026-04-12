package com.rentio.note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rentio.note.dto.response.DefaultNoteResponse;
import com.rentio.note.model.Note;
import com.rentio.note.repository.NoteRepository;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Page<DefaultNoteResponse> listNotes(Pageable pageable) {
        Page<Note> listNotes = noteRepository.findAll(pageable);

        return listNotes.map(note -> DefaultNoteResponse.from(note));
    }
}

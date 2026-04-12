package com.rentio.note.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rentio.note.enums.NoteEntityType;
import com.rentio.note.model.Note;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    Page<Note> findByEntityTypeAndEntityId(NoteEntityType entityType, UUID entityId, Pageable pageable);
}

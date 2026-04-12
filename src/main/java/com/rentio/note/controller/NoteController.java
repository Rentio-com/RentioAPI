package com.rentio.note.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rentio.common.dto.PaginationAndSortResponse;
import com.rentio.note.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class NoteController {
    
    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public PaginationAndSortResponse<?> listNotes(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        return PaginationAndSortResponse.from(noteService.listNotes(pageable));
    }
    
}

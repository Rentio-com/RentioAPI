package com.rentio.common.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public record PaginationAndSortResponse<T>(List<T> data, int page, int size, long totalElements, int totalPages) {
    
    public static<T> PaginationAndSortResponse<T> from (Page<T> page) {
        return new PaginationAndSortResponse<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}

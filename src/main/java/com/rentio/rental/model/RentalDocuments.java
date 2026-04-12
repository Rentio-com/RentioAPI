package com.rentio.rental.model;

import java.util.UUID;


import com.rentio.common.model.BaseEntity;
import com.rentio.rental.enums.DocumentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "rental_documents")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RentalDocuments extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private UUID id;

    @Column(name = "rental_id", nullable = false)
    private UUID rental_id;

    @Column(name = "uploaded_by", nullable = false)
    private UUID uploadedBy;

    @Column(name = "document_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank
    @NonNull
    private DocumentType documentType;

    @Column(name = "file_url", nullable = false)
    @NotBlank
    @NonNull
    private String fileUrl;
}

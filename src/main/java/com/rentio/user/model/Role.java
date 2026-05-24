package com.rentio.user.model;

import java.util.UUID;

import com.rentio.common.model.BaseEntity;
import com.rentio.user.enums.RoleCode;

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
@Table(name = "roles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(name = "company_id", nullable = true, updatable = false)
    private UUID companyId;

    @Column(name = "role_name", nullable = false)
    @NonNull
    @NotBlank
    private String roleName;

    @Column(name = "role_code", nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    private RoleCode roleCode;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "is_system", nullable = false)
    private boolean isSystem;
}

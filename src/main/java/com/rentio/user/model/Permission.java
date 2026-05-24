package com.rentio.user.model;

import java.util.UUID;

import com.rentio.common.model.BaseEntity;
import com.rentio.user.enums.PermissionCode;

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
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Permission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false, updatable = false )
    private UUID id;

    @Column(name = "name", nullable = false)
    @NonNull
    @NotBlank
    private String name;

    @Column(name = "permission_code", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PermissionCode permissionCode;

    @Column(name = "description", nullable = true)
    private String description;
}

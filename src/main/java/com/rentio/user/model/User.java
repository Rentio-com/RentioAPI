package com.rentio.user.model;

import java.time.LocalDateTime;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class User extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "company_id", updatable = false, nullable = false)
    @NonNull
    private UUID companyId;

    @Column(name = "first_name", nullable = false)
    @NonNull
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NonNull
    @NotBlank
    private String lastName;

    @Column(name = "role_code", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RoleCode roleCode = RoleCode.WORKER;

    @Column(name = "email", nullable = false, unique = true)
    @NonNull
    @NotBlank
    @Email
    private String email;

    @Column(name = "phone_number", nullable = false)
    @NonNull
    @NotBlank
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @NonNull
    @NotBlank
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "is_master", nullable = false)
    @Builder.Default
    private boolean isMaster = false;

    @Column(name = "last_login", nullable = false)
    private LocalDateTime lastLogin;
}

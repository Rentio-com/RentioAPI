# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

Use the Maven wrapper (`./mvnw` on Unix, `mvnw.cmd` on Windows / PowerShell).

```powershell
# Start Postgres (+ pgAdmin on :5050) — required before running the app
docker compose up -d db

# Run the app (port 8080)
./mvnw spring-boot:run

# Build a jar
./mvnw clean package

# Run all tests
./mvnw test

# Run a single test class
./mvnw test "-Dtest=RentioApplicationTests"

# Run a single test method
./mvnw test "-Dtest=RentioApplicationTests#contextLoads"
```

Local DB connection is hardcoded in `application.properties` (jdbc:postgresql://localhost:5432/rental_db, admin / super_secret_password_123) and matches `docker-compose.yml`.

## Architecture

Spring Boot 4.0.5 modular monolith built on **Spring Modulith** (`spring-modulith-starter-core` is on the classpath). Each top-level package under `com.rentio` is intended to be an independent module that could later be extracted into a microservice. Java 17, PostgreSQL + JPA/Hibernate, MapStruct, Lombok.

### Module layout

```
com.rentio
├── RentioApplication        Spring Boot entry point
├── common                   Shared infra ONLY (BaseEntity, exceptions, config, security, pagination DTO)
├── user                     Users, roles, permissions (no service/repo yet)
├── customer                 Customer profiles
├── fleet                    Vehicles + insurance + vehicle documents
├── rental                   Rentals, addons, costs, documents, inspection protocols
├── note                     Polymorphic notes attached to any entity (entityType + entityId)
├── catalog                  Reserved / empty
└── billing                  Reserved / empty
```

Inside each module the layout is `controller/`, `service/`, `repository/`, `model/` (JPA entities), `dto/`, `enums/`, `mapper/` (MapStruct), and `interfaces/common/` (View interfaces used as polymorphic service return types — e.g. `VehicleView`, `InsuranceView`).

### Module boundaries — the rule to enforce

Modules must communicate **only through public service classes**, never via another module's repository or entity. The codebase currently has known violations that should not be extended:

- `customer.CustomerService` directly uses `rental.RentalRepository`
- `rental.RentalService` directly uses `note.NoteRepository`
- `VehicleType` / `VehicleSegment` enums are duplicated in both `common.enums` and `fleet.enums`
- `common.model.Addon` is a business entity living in infra; conceptually belongs in `catalog`

When wiring new cross-module behavior, prefer:
1. Calling the other module's `*Service` class, OR
2. Publishing an event with `ApplicationEventPublisher` and consuming it with `@ApplicationModuleListener` (Spring Modulith — gives async + `AFTER_COMMIT` + new transaction in one annotation).

Cross-module relationships use a UUID foreign key column (e.g. `rentals.vehicle_id`, `rental_addons.addon_id`) — never a JPA `@ManyToOne` across modules.

### Persistence conventions

- All entities extend `common.model.BaseEntity` (audit timestamps via `AuditingEntityListener`, enabled by `common.config.JpaConfig` `@EnableJpaAuditing`).
- Entities use `@SuperBuilder` + `@NoArgsConstructor(access = PROTECTED)` + `@AllArgsConstructor` (Lombok).
- IDs are `UUID` with `GenerationType.UUID`.
- Every business entity has a `companyId` column (multi-tenancy). Set it on create from `CurrentUserProvider.getCompanyId()` — **do not** accept it from request bodies.
- Enums on entities use `@Enumerated(EnumType.STRING)`.
- JSON columns use `@JdbcTypeCode(SqlTypes.JSON)` (e.g. `Vehicle.features`, `RentalProtocol.photos` / `damageMarks`).
- `spring.jpa.hibernate.ddl-auto=create-drop` and `spring.sql.init.mode=always` — schema is wiped on every startup and reseeded from `src/main/resources/data.sql`. Flyway is on the classpath but **disabled** (`spring.flyway.enabled=false`); there are no migrations yet.

### Security / current user

- `common.security.SecurityConfig` permits all requests and disables CSRF — there is no real auth yet.
- `common.security.CurrentUserProvider` returns hardcoded `companyId` / `userId` UUIDs (`c0000000-...-0001` / `f1111111-...-1111`). Use this provider everywhere `companyId` or `createdBy` is needed; do not hardcode UUIDs at call sites.
- The seed data in `data.sql` is scoped to that hardcoded `companyId`.

### Error handling

`common.exception.GlobalExceptionHandler` (`@RestControllerAdvice`) maps:

- `MethodArgumentNotValidException` → 400 + `FieldValidationErrorResponse` (field-level list)
- `NoSuchElementException` → 404 + `ErrorResponse`. Services throw this via `repository.findById(id).orElseThrow()` — keep that pattern instead of introducing a custom NotFound exception.
- `BussinesException(code, field)` → 400 + `FieldValidationError`. Throw this for domain rule violations; use constants from `common.validation.ValidationError` for the `code`.
- `HttpMessageNotReadableException` → 400 (currently parses the message string positionally — fragile).

### Controllers

- Base path convention: `/api/v1/<resource>` (followed by `VehicleController`; other controllers predate the convention and should be aligned when touched).
- Paginated list endpoints take `Pageable` via `@PageableDefault(...)` and return `PaginationAndSortResponse.from(page)`.
- Services return `View` interfaces (e.g. `VehicleView`) so controllers and other modules never depend on JPA entities.

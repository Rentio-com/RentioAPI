-- data.sql — auto-runs after schema creation
INSERT INTO vehicles (id, company_id, make, model, production_year, license_plate, vin, engine_info, engine_type, transmission, fuel_level, mileage, status, created_at, updated_at, segment, type)
VALUES
    (gen_random_uuid(), gen_random_uuid(), 'Toyota', 'Corolla', 2023, 'KR 12345', 'JTD12345678901234', '1.8L Hybrid', 'HYBRID', 'AUTOMATIC', 100, 15000, 'READY_TO_RENT', NOW(), NOW(), 'B', 'COMPACT'),
    (gen_random_uuid(), gen_random_uuid(), 'VW', 'Golf', 2022, 'KR 67890', 'WVW12345678901234', '2.0 TDI', 'DIESEL', 'MANUAL', 80, 42000, 'AVAILABLE', NOW(), NOW(), 'C', 'SEDAN');

INSERT INTO rentals (id, company_id, customer_id, vehicle_id, vehicle_type, vehicle_segment, scheduled_pickup, scheduled_return, actual_pickup, actual_return, daily_rate, estimated_total, final_total, pickup_address, pickup_city, pickup_lat, pickup_lng, return_address, return_city, return_lat, return_lng, rental_status)
VALUES
    -- Active rental
    ('d1111111-1111-1111-1111-111111111111', 'c0000000-0000-0000-0000-000000000001', 'b1111111-1111-1111-1111-111111111111', 'a3333333-3333-3333-3333-333333333333', 'SEDAN', 'D', '2026-04-10T10:00:00', '2026-04-17T10:00:00', '2026-04-10T10:15:00', NULL, 180.00, 1260.00, NULL, 'Balice Airport', 'Kraków', 50.0777, 19.7848, 'Balice Airport', 'Kraków', 50.0777, 19.7848, 'ACTIVE'),

    -- Future reservation
    ('d2222222-2222-2222-2222-222222222222', 'c0000000-0000-0000-0000-000000000001', 'b2222222-2222-2222-2222-222222222222', 'a1111111-1111-1111-1111-111111111111', 'HATCHBACK', 'C', '2026-04-20T09:00:00', '2026-04-25T09:00:00', NULL, NULL, 120.00, 600.00, NULL, 'Kraków Główny', 'Kraków', 50.0680, 19.9471, 'ul. Długa 15', 'Kraków', 50.0614, 19.9372, 'CONFIRMED'),

    -- Completed rental
    ('d3333333-3333-3333-3333-333333333333', 'c0000000-0000-0000-0000-000000000001', 'b3333333-3333-3333-3333-333333333333', 'a2222222-2222-2222-2222-222222222222', 'SEDAN', 'C', '2026-03-01T08:00:00', '2026-03-08T08:00:00', '2026-03-01T08:20:00', '2026-03-08T09:30:00', 140.00, 980.00, 1010.00, 'ul. Floriańska 10', 'Kraków', 50.0637, 19.9410, 'ul. Floriańska 10', 'Kraków', 50.0637, 19.9410, 'COMPLETED');


INSERT INTO customers (
    id, company_id, first_name, last_name, phone_number, email,
    personal_id_type, personal_id_number,
    document_number, document_expiry, document_country,
    driver_license_number, driver_license_expiry, driver_license_no_expiry, driver_license_country,
    customer_type, company_name, tax_id,
    blacklisted, blacklisted_reason, notes,
    created_at, updated_at
) VALUES
    -- Polish individual
    ('b1111111-1111-1111-1111-111111111111', 'c0000000-0000-0000-0000-000000000001',
     'Jan', 'Kowalski', '+48 500 100 200', 'jan.kowalski@email.com',
     'PESEL', '85021578943',
     'ABS 123456', '2029-05-15', 'PL',
     'PL/DL/00123/2020', '2030-01-10', false, 'PL',
     'INDIVIDUAL', NULL, NULL,
     false, NULL, NULL,
     NOW(), NOW()),

    -- Polish individual with no-expiry license
    ('b2222222-2222-2222-2222-222222222222', 'c0000000-0000-0000-0000-000000000001',
     'Anna', 'Nowak', '+48 500 300 400', 'anna.nowak@email.com',
     'PESEL', '90071234567',
     'CBD 789012', '2031-08-20', 'PL',
     'PL/DL/00456/2005', NULL, true, 'PL',
     'INDIVIDUAL', NULL, NULL,
     false, NULL, 'Stały klient, zawsze oddaje na czas',
     NOW(), NOW()),

    -- German tourist
    ('b3333333-3333-3333-3333-333333333333', 'c0000000-0000-0000-0000-000000000001',
     'Hans', 'Mueller', '+49 170 1234567', 'hans.mueller@gmail.com',
     'NONE', NULL,
     'C4KKPX2R7', '2028-03-12', 'DE',
     'DE/DL/M789/2019', '2034-06-01', false, 'DE',
     'INDIVIDUAL', NULL, NULL,
     false, NULL, NULL,
     NOW(), NOW()),

    -- Corporate client
    ('b4444444-4444-4444-4444-444444444444', 'c0000000-0000-0000-0000-000000000001',
     'Marek', 'Wiśniewski', '+48 12 345 6789', 'flota@translogic.pl',
     'PESEL', '78110523456',
     'EFG 345678', '2027-11-30', 'PL',
     'PL/DL/00789/2018', '2028-07-15', false, 'PL',
     'CORPORATE', 'TransLogic Sp. z o.o.', '6791234567',
     false, NULL, 'Umowa flotowa, faktura miesięczna',
     NOW(), NOW()),

    -- Blacklisted customer
    ('b5555555-5555-5555-5555-555555555555', 'c0000000-0000-0000-0000-000000000001',
     'Tomasz', 'Zając', '+48 500 900 800', NULL,
     'PESEL', '95030912345',
     'HIJ 567890', '2026-12-01', 'PL',
     'PL/DL/00999/2021', '2031-04-20', false, 'PL',
     'INDIVIDUAL', NULL, NULL,
     true, 'Zwrócił auto z poważnymi uszkodzeniami, odmówił zapłaty', NULL,
     NOW(), NOW());


INSERT INTO notes (
    id, company_id, entity_id, entity_type, author_id, content,
    resolved, resolved_at, resolved_by,
    created_at, updated_at
) VALUES
    -- Vehicle notes
    ('e1111111-1111-1111-1111-111111111111', 'c0000000-0000-0000-0000-000000000001',
     'a1111111-1111-1111-1111-111111111111', 'VEHICLE',
     'f1111111-1111-1111-1111-111111111111',
     'Wymiana oleju przy 45000 km',
     true, '2026-03-15T14:00:00', 'f1111111-1111-1111-1111-111111111111',
     '2026-03-10T09:00:00', '2026-03-15T14:00:00'),

    ('e2222222-2222-2222-2222-222222222222', 'c0000000-0000-0000-0000-000000000001',
     'a2222222-2222-2222-2222-222222222222', 'VEHICLE',
     'f1111111-1111-1111-1111-111111111111',
     'Rysa na zderzaku tylnym, do lakiernika',
     false, NULL, NULL,
     '2026-04-10T11:30:00', '2026-04-10T11:30:00'),

    -- Rental notes
    ('e3333333-3333-3333-3333-333333333333', 'c0000000-0000-0000-0000-000000000001',
     'd1111111-1111-1111-1111-111111111111', 'RENTAL',
     'f1111111-1111-1111-1111-111111111111',
     'Klient prosi o przedłużenie do 20.04',
     false, NULL, NULL,
     '2026-04-12T08:00:00', '2026-04-12T08:00:00'),

    ('e4444444-4444-4444-4444-444444444444', 'c0000000-0000-0000-0000-000000000001',
     'd3333333-3333-3333-3333-333333333333', 'RENTAL',
     'f1111111-1111-1111-1111-111111111111',
     'Spóźniony zwrot o 1.5h, naliczona dopłata',
     true, '2026-03-08T10:00:00', 'f1111111-1111-1111-1111-111111111111',
     '2026-03-08T09:30:00', '2026-03-08T10:00:00'),

    -- Customer notes
    ('e5555555-5555-5555-5555-555555555555', 'c0000000-0000-0000-0000-000000000001',
     'b1111111-1111-1111-1111-111111111111', 'CUSTOMER',
     'f1111111-1111-1111-1111-111111111111',
     'Stały klient, preferuje automaty',
     false, NULL, NULL,
     '2026-02-01T10:00:00', '2026-02-01T10:00:00'),

    ('e6666666-6666-6666-6666-666666666666', 'c0000000-0000-0000-0000-000000000001',
     'b5555555-5555-5555-5555-555555555555', 'CUSTOMER',
     'f1111111-1111-1111-1111-111111111111',
     'Odmówił zapłaty za uszkodzenia, sprawa u prawnika',
     false, NULL, NULL,
     '2026-03-20T16:00:00', '2026-03-20T16:00:00');
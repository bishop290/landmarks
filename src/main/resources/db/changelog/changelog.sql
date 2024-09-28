--liquibase formatted sql

--changeset Grigorii_Kuznetsov:1
create table locality (
    id bigserial primary key,
    name varchar(100) not null,
    population_size Integer,
    metro boolean);

--changeset Grigorii_Kuznetsov:2
create table service (
    id bigserial primary key,
    name varchar(100),
    description varchar(200));

--changeset Grigorii_Kuznetsov:3
--preconditions onFail:CONTINUE
--precondition-sql-check expectedResult:0 select count(*) from pg_type where typname = 'type_of_attraction'
create type type_of_attraction as enum
('CASTLE', 'PARK', 'MUSEUM', 'ARCHEOLOGY', 'RESERVE');

--changeset Grigorii_Kuznetsov:4
create table landmark (
    id bigserial primary key,
    name varchar(100),
    date_created date,
    description varchar(200),
    attraction type_of_attraction not null,
    locality_id bigint references locality (id) not null);

--changeset Grigorii_Kuznetsov:5
create table landmark_service (
    id bigserial primary key,
    landmark_id bigint not null references landmark (id) on delete cascade,
    service_id bigint not null references service (id) on delete cascade);

--changeset Grigorii_Kuznetsov:6
alter table landmark
alter column attraction type varchar(100);
drop type type_of_attraction;

--changeset Grigorii_Kuznetsov:7
insert into locality(name, population_size, metro)
values
('Locality-1', 20000000, true),
('Locality-2', 100000, true),
('Locality-3', 1000, false),
('Locality-4', 10000, false),
('Locality-5', 2000, false),
('Locality-6', 300000, false);

insert into landmark(name, date_created, description, attraction, locality_id)
values
('A_Landmark-1', '1922-02-02', 'Description-1', 'CASTLE', 1),
('B_Landmark-2', '1922-02-03', 'Description-2', 'CASTLE', 1),
('C_Landmark-3', '1922-02-04', 'Description-3', 'ARCHEOLOGY', 1),
('D_Landmark-4', '1922-02-05', 'Description-4', 'ARCHEOLOGY', 2),
('I_Landmark-5', '1922-02-06', 'Description-5', 'ARCHEOLOGY', 3),
('F_Landmark-6', '1922-02-07', 'Description-6', 'ARCHEOLOGY', 4),
('G_Landmark-7', '1922-02-08', 'Description-7', 'ARCHEOLOGY', 5);

insert into service(name, description)
values
('Service-1', 'Description-1'),
('Service-2', 'Description-2'),
('Service-3', 'Description-3'),
('Service-4', 'Description-4'),
('Service-5', 'Description-5'),
('Service-6', 'Description-6'),
('Service-7', 'Description-7'),
('Service-8', 'Description-8'),
('Service-9', 'Description-9'),
('Service-10', 'Description-10');

insert into landmark_service(landmark_id, service_id)
values
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(3, 5),
(4, 6),
(5, 7),
(6, 8),
(7, 9);
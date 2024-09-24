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





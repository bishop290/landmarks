--liquibase formatted sql

--changeset Grigorii_Kuznetsov:1
create table locality (
  id serial primary key,
  name varchar(100),
  population_size Integer,
  metro boolean);
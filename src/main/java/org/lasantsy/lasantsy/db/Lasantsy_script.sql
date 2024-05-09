DROP DATABASE IF EXISTS lasantsy;

CREATE DATABASE lasantsy;

\c lasantsy;

CREATE TABLE IF not exists station (
    id bigserial primary key,
    name varchar(50) not null,
    longitude varchar(50) not null,
    latitude varchar(50) not null,
    employee_number int not null
);

CREATE TABLE IF not exists product (
    id bigserial primary key,
    name varchar(50) not null,
    price double precision not null
);

CREATE TABLE IF not exists movement (
    id bigserial primary key,
    id_station bigint not null references station(id),
    id_production bigint not null references product(id),
    value double precision not null,
    type varchar(50),
);

CREATE TABLE IF not exists evaporation_rate (
    id bigserial primary key,
    id_station bigint not null references station(id),
    value double precision not null
);

CREATE TABLE IF not exists stock (
    id bigserial primary key,
    id_station bigint not null references station(id),
    valeur double precision not null
);
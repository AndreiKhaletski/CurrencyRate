CREATE ROLE "rate_app" WITH
	LOGIN
	NOSUPERUSER
	CREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT -1
	PASSWORD '855312';

CREATE DATABASE currency
    WITH
    OWNER = "rate_app"
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA app
    AUTHORIZATION "rate_app";

CREATE TABLE app.courses(
    uuid UUID NOT NULL PRIMARY KEY,
    cur_id integer,
    date timestamp WITHOUT TIME ZONE NOT NULL,
    cur_abbreviation VARCHAR(255),
    cur_scale integer,
    cur_name VARCHAR(255),
    cur_official_rate double precision
);

ALTER TABLE IF EXISTS app.courses
    OWNER to "rate_app";

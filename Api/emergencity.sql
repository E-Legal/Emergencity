psql
DROP ROLE admin;
DROP DATABASE emergencity;
CREATE DATABASE emergencity;
CREATE USER admin WITH PASSWORD 'admin';
ALTER ROLE admin WITH SUPERUSER;
\c emergencity
CREATE TABLE EMERGENCITY_TRAFFICLIGHT(
id UUID PRIMARY KEY,
city text NOT NULL,
x text NOT NULL,
y text NOT NULL,
z text NOT NULL
);
CREATE TABLE EMERGENCITY_USER(
id UUID PRIMARY KEY,
update_at TIMESTAMP WITH TIME ZONE,
created_at TIMESTAMP WITH TIME ZONE,
name text NOT NULL UNIQUE,
password text NOT NULL,
admin boolean,
superuser boolean,
algorithm boolean,
token text,
token_update TIMESTAMP WITH TIME ZONE,
token_end TIMESTAMP WITH TIME ZONE
);
CREATE TABLE EMERGENCITY_DESTINATION(
id UUID PRIMARY KEY,
y_start text,
x_start	text,
y_end text,
x_end text,
course text,
time text,
distance text
);

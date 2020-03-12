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
first_name text,
last_name text,
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

CREATE TABLE EMERGENCITY_BARRACK(
id UUID PRIMARY KEY,
name text NOT NULL,
city text NOT NULL
);

CREATE TABLE EMERGENCITY_VEHICLE(
id UUID PRIMARY KEY,
model text NOT NULL,
registration text NOT NULL
);

CREATE TABLE EMERGENCITY_BARRACK_VEHICLE(
vehicle_id UUID NOT NULL REFERENCES EMERGENCITY_VEHICLE ON DELETE CASCADE,
barrack_id UUID NOT NULL REFERENCES EMERGENCITY_BARRACK	ON DELETE CASCADE,
CONSTRAINT leanova_vehicle_barrack_unique UNIQUE (vehicle_id, barrack_id)
);

CREATE TABLE EMERGENCITY_BARRACK_USER(
user_id UUID	NOT NULL REFERENCES EMERGENCITY_USER	ON DELETE CASCADE,
barrack_id UUID	NOT NULL REFERENCES EMERGENCITY_BARRACK ON DELETE CASCADE,
CONSTRAINT leanova_user_barrack_unique UNIQUE (user_id, barrack_id)
);

INSERT INTO EMERGENCITY_USER (id, name, password, admin, superuser, algorithm) VALUES ('0e37df36-f698-11e6-8dd4-cb9ced3df976', 'test', 'yoho', true, true, true);

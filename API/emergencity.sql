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
z text NOT NULL,
actif boolean DEFAULT false
);
CREATE TABLE EMERGENCITY_USER(
id UUID PRIMARY KEY,
update_at TIMESTAMP WITH TIME ZONE,
created_at TIMESTAMP WITH TIME ZONE,
name text NOT NULL UNIQUE,
password text NOT NULL
);

CREATE TABLE EMERGENCITY_USER_PROFIL(
id UUID PRIMARY KEY,
user_id UUID NOT NULL,
first_name text NOT NULL UNIQUE,
last_name text NOT NULL,
job text NOT NULL
);

CREATE TABLE EMERGENCITY_ADMIN (
user_id UUID NOT NULL REFERENCES EMERGENCITY_USER ON DELETE CASCADE UNIQUE
);

CREATE TABLE EMERGENCITY_SUPER_USER (
user_id UUID NOT NULL REFERENCES EMERGENCITY_USER ON DELETE CASCADE UNIQUE
);

CREATE TABLE EMERGENCITY_ALGO (
user_id UUID NOT NULL REFERENCES EMERGENCITY_USER ON DELETE CASCADE UNIQUE
);

CREATE TABLE EMERGENCITY_TOKEN(
id UUID PRIMARY KEY,
user_id UUID NOT NULL REFERENCES EMERGENCITY_USER ON DELETE CASCADE,
token text,
token_update TIMESTAMP WITH TIME ZONE,
token_end TIMESTAMP WITH TIME ZONE
);
CREATE TABLE EMERGENCITY_DESTINATION(
id UUID PRIMARY KEY,
y_start text,
x_start text,
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
registration text NOT NULL UNIQUE
);

CREATE TABLE EMERGENCITY_BARRACK_VEHICLE(
vehicle_id UUID NOT NULL REFERENCES EMERGENCITY_VEHICLE ON DELETE CASCADE,
barrack_id UUID NOT NULL REFERENCES EMERGENCITY_BARRACK ON DELETE CASCADE,
CONSTRAINT leanova_vehicle_barrack_unique UNIQUE (vehicle_id, barrack_id)
);

CREATE TABLE EMERGENCITY_BARRACK_USER(
user_id UUID NOT NULL REFERENCES EMERGENCITY_USER ON DELETE CASCADE,
barrack_id UUID NOT NULL REFERENCES EMERGENCITY_BARRACK ON DELETE CASCADE,
CONSTRAINT leanova_user_barrack_unique UNIQUE (user_id, barrack_id)
);

INSERT INTO EMERGENCITY_USER (id, name, password) VALUES ('0e37df36-f698-11e6-8dd4-cb9ced3df976', 'test', 'yoho');
INSERT INTO EMERGENCITY_SUPER_USER (user_id) VALUES ('0e37df36-f698-11e6-8dd4-cb9ced3df976');
INSERT INTO EMERGENCITY_ADMIN (user_id) VALUES ('0e37df36-f698-11e6-8dd4-cb9ced3df976');

INSERT INTO EMERGENCITY_USER (id, name, password) VALUES ('d853e812-83cd-11ea-bc55-0242ac130003', 'algo', 'cocolito');
INSERT INTO EMERGENCITY_ALGO (user_id) VALUES ('d853e812-83cd-11ea-bc55-0242ac130003');

INSERT INTO EMERGENCITY_USER (id, name, password) VALUES ('a3850160-83ce-11ea-bc55-0242ac130003', 'colin_test', '123Soleil');
INSERT INTO EMERGENCITY_SUPER_USER (user_id) VALUES ('a3850160-83ce-11ea-bc55-0242ac130003');
INSERT INTO EMERGENCITY_ADMIN (user_id) VALUES ('a3850160-83ce-11ea-bc55-0242ac130003');

INSERT INTO EMERGENCITY_USER (id, name, password) VALUES ('ff60e40c-83d0-11ea-bc55-0242ac130003', 'quentin_test', '123Soleil');
INSERT INTO EMERGENCITY_SUPER_USER (user_id) VALUES ('ff60e40c-83d0-11ea-bc55-0242ac130003');
INSERT INTO EMERGENCITY_ADMIN (user_id) VALUES ('ff60e40c-83d0-11ea-bc55-0242ac130003');

INSERT INTO EMERGENCITY_USER (id, name, password) VALUES ('23f733f2-83d1-11ea-bc55-0242ac130003', 'eliott_test', '123Soleil');
INSERT INTO EMERGENCITY_SUPER_USER (user_id) VALUES ('23f733f2-83d1-11ea-bc55-0242ac130003');

INSERT INTO EMERGENCITY_USER (id, name, password) VALUES ('411ba224-83d1-11ea-bc55-0242ac130003', 'sophie_test', '123Soleil');
INSERT INTO EMERGENCITY_SUPER_USER (user_id) VALUES ('411ba224-83d1-11ea-bc55-0242ac130003');

INSERT INTO EMERGENCITY_USER (id, name, password) VALUES ('4aa87ed4-83d1-11ea-bc55-0242ac130003', 'eloise_test', '123Soleil');
INSERT INTO EMERGENCITY_SUPER_USER (user_id) VALUES ('4aa87ed4-83d1-11ea-bc55-0242ac130003');

INSERT INTO EMERGENCITY_USER (id, name, password) VALUES ('09adbcf4-83d2-11ea-bc55-0242ac130003', 'fabien_test', '123Soleil');
INSERT INTO EMERGENCITY_SUPER_USER (user_id) VALUES ('09adbcf4-83d2-11ea-bc55-0242ac130003');

INSERT INTO EMERGENCITY_USER (id, name, password) VALUES ('1ff9c67e-83d2-11ea-bc55-0242ac130003', 'yanis_test', '123Soleil');
INSERT INTO EMERGENCITY_SUPER_USER (user_id) VALUES ('1ff9c67e-83d2-11ea-bc55-0242ac130003');
-- drop tables before each application run.
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS party;
DROP TABLE IF EXISTS party_state;
DROP TABLE IF EXISTS party_capacity;
DROP TABLE IF EXISTS interaction;
DROP TABLE IF EXISTS interaction_type;

CREATE TABLE "member" (
  "id" bigint auto_increment,
  "name" text,
  "email" text,
  "address" text,
  "contact_number" integer,
  "solicitor" text,
  PRIMARY KEY ("id")
);

CREATE TABLE "party" (
  "id"  bigint auto_increment,
  "party_id" integer,
  "party_capacity" text,
  "party_state" text,
  "last_updated" timestamp,
  "represented_by" integer,
  "parent_id" integer,
  "ccd_reference_id" text,
  PRIMARY KEY ("id")
);

CREATE TABLE "interaction" (
  "id"  bigint auto_increment,
  "interaction_by" integer,
  "interaction_date" timestamp,
  "interaction_type" text,
  "description" json,
  "parent_id" integer,
  "ccd_reference_id" text,
  PRIMARY KEY ("id")
);

CREATE TABLE "interaction_type" (
  "id"  bigint auto_increment,
  "name" text,
  PRIMARY KEY ("id")
);

CREATE TABLE "party_state" (
  "id"  bigint auto_increment,
  "name" text,
  PRIMARY KEY ("id")
);

CREATE TABLE "party_capacity" (
  "id"  bigint auto_increment,
  "name" text,
  PRIMARY KEY ("id")
);

-- Reference Data
-- Users
INSERT INTO "member" ("name") VALUES('Green'); --ID#: 1
INSERT INTO "member" ("name") VALUES('Blue'); --ID#: 2
INSERT INTO "member" ("name") VALUES('Yellow'); --ID#: 3
INSERT INTO "member" ("name", "solicitor") VALUES('Green Solicitors', 'Y'); --ID#: 4
INSERT INTO "member" ("name", "solicitor") VALUES('Yellow Solicitors', 'Y'); --ID#: 5

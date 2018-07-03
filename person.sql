/*
 Navicat Premium Data Transfer

 Source Server         : PostgreSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 90402
 Source Host           : localhost:5432
 Source Catalog        : person
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90402
 File Encoding         : 65001

 Date: 04/07/2018 01:39:51
*/


-- ----------------------------
-- Sequence structure for person_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."person_sequence";
CREATE SEQUENCE "public"."person_sequence" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS "public"."person";
CREATE TABLE "public"."person" (
  "id" int4 NOT NULL,
  "firstname" varchar(255) COLLATE "pg_catalog"."default",
  "lastname" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "age" int4
)
;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO "public"."person" VALUES (1, 'Shoxrux', 'Narzullayev', 'shoxrux_mts@mail.ru', 24);
INSERT INTO "public"."person" VALUES (2, 'Farrux', 'Normurodov', 'fn@mail.ru', 30);
INSERT INTO "public"."person" VALUES (5, 'User2', 'UserName2', 'user2@mail.ru', 32);
INSERT INTO "public"."person" VALUES (7, 'User3', 'User3', 'user3@mail.ru', 35);
INSERT INTO "public"."person" VALUES (9, 'User3', 'User4', 'user2@mail.ru', 54);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."person_sequence"', 2, false);

-- ----------------------------
-- Primary Key structure for table person
-- ----------------------------
ALTER TABLE "public"."person" ADD CONSTRAINT "person_pkey" PRIMARY KEY ("id");

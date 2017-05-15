DROP TABLE IF EXISTS "public"."person";
CREATE TABLE "public"."person" (
"id" int4 DEFAULT nextval('person_id_seq'::regclass) NOT NULL,
"code" varchar(32) COLLATE "default" NOT NULL,
"name" varchar(50) COLLATE "default" NOT NULL,
"document" varchar(15) COLLATE "default" NOT NULL,
"birthday" date NOT NULL,
"height" numeric(10,2) NOT NULL,
"status" bool DEFAULT true NOT NULL
) WITH (OIDS=FALSE);

ALTER TABLE "public"."person" ADD UNIQUE ("code");

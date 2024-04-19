-- Adminer 4.8.1 PostgreSQL 16.2 (Debian 16.2-1.pgdg120+2) dump

\connect
"images";

DROP TABLE IF EXISTS "parent_entity";
CREATE TABLE "public"."parent_entity"
(
    "img_type"       character varying(31) NOT NULL,
    "generated_name" character varying(40) NOT NULL,
    "date_creation"  timestamp(6),
    "file_path"      character varying(255),
    "mime_type"      character varying(255),
    "original_name"  character varying(255),
    "description"    character varying(255),
    CONSTRAINT "parent_entity_pkey" PRIMARY KEY ("generated_name")
) WITH (oids = false);

INSERT INTO "parent_entity" ("img_type", "generated_name", "date_creation", "file_path", "mime_type", "original_name",
                             "description")
VALUES ('a', '00000000-0000-0000-0000-000000000000', '2024-04-16 21:09:55.735067', '/m/w/g/', 'image/jpeg',
        'pets-icon.jpg', NULL),
       ('a', 'f1996e19-fb0d-4bd7-b86f-90a914be24fe', '2024-04-19 14:01:59.769311', '/h/m/t/', 'image/jpeg',
        'photo_2024-04-19_14-01-45.jpg', NULL),
       ('a', 'a0a8c41e-fb4e-4245-8b1a-40e9d10563de', '2024-04-19 14:11:20.210305', '/n/q/x/', 'image/jpeg',
        'photo_2024-04-19_14-10-01.jpg', NULL),
       ('a', 'e55fadc3-ac61-4e94-ad49-b19c73b9ba38', '2024-04-19 14:24:25.360806', '/l/e/p/', 'image/jpeg',
        'photo_2024-04-19_14-10-05.jpg', NULL),
       ('a', '4c472274-1c31-4e83-bfe4-0827d9dd0adb', '2024-04-19 14:24:48.476847', '/c/y/q/', 'image/jpeg',
        'photo_2024-04-19_14-10-05.jpg', NULL);

-- 2024-04-19 13:13:28.037676+00
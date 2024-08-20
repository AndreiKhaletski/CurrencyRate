\c krainet

CREATE SCHEMA app AUTHORIZATION "user_krainet";

CREATE TABLE app.projects (
    uuid UUID PRIMARY KEY,
    name VARCHAR(255),
    dt_create TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    dt_update TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    description VARCHAR(255)
);

ALTER TABLE IF EXISTS app.projects
    OWNER to "user_krainet";

CREATE TABLE app.users (
    uuid UUID PRIMARY KEY,
	project_uuid UUID REFERENCES app.projects(uuid),
    dt_create TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    dt_update TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    mail VARCHAR(255),
    fio VARCHAR(255),
    role VARCHAR(255),
    status VARCHAR(255),
    password VARCHAR(255)
);

ALTER TABLE IF EXISTS app.users
    OWNER to "user_krainet";

CREATE TABLE IF NOT EXISTS app.verification (
    mail VARCHAR(255) PRIMARY KEY,
    code VARCHAR(255),
    status VARCHAR(255)
);

ALTER TABLE app.verification
    OWNER TO "user_krainet";

CREATE TABLE app.records (
    uuid UUID PRIMARY KEY,
    uuid_user UUID,
    uuid_project UUID ,
    start_time TIMESTAMP WITHOUT TIME ZONE,
    end_time TIMESTAMP WITHOUT TIME ZONE,
    status VARCHAR(255),
    duration INTEGER,
    FOREIGN KEY (uuid_user) REFERENCES app.users(uuid),
    FOREIGN KEY (uuid_project) REFERENCES app.projects(uuid)
);

ALTER TABLE IF EXISTS app.records
    OWNER to "user_krainet";

INSERT INTO app.users (
    uuid,
    dt_create,
    dt_update,
    mail,
    fio,
    role,
    status,
    password
)
VALUES (
    '00000000-0000-0000-0000-000000000000',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'admin@gmail.com',
    'Admin Adminovich',
    'ROLE_ADMIN',
    'ACTIVATED',
    '$2a$10$kVx33idOsssroHLhLR7Bgu1WkJQ.N3Cy0Ma3u6Lcy.8GPuxwnbxmq'
);
DROP TABLE test1;

CREATE TABLE users(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    username VARCHAR (255) UNIQUE,
    password VARCHAR (255),
    first_name VARCHAR (255),
    last_name VARCHAR (255),
    phone_number VARCHAR (255),
    pesel VARCHAR (255) UNIQUE
);

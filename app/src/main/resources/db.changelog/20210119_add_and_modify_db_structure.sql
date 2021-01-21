ALTER TABLE app_user
    RENAME TO "user";
ALTER TABLE app_authority
    RENAME TO authority;
ALTER TABLE app_user_authority
    RENAME TO user_authority;

CREATE TABLE section
(
    id   BIGINT,

    name TEXT UNIQUE,

    PRIMARY KEY (id)
);

CREATE TABLE category
(
    id         BIGINT,
    section_id BIGINT,

    name       TEXT UNIQUE,

    PRIMARY KEY (id),
    CONSTRAINT section_id_fk
        FOREIGN KEY (section_id) REFERENCES section (id)
);

CREATE TABLE auction
(
    id          BIGINT,
    creator_id  BIGINT,

    title       TEXT,
    description TEXT,
    price       BIGINT,
    category    TEXT,

    PRIMARY KEY (id),
    CONSTRAINT user_id_fk
        FOREIGN KEY (creator_id) REFERENCES "user" (id),
    CONSTRAINT category_name_fk
        FOREIGN KEY (category) REFERENCES category (name)
);

CREATE TABLE photo
(
    id         BIGINT,
    auction_id BIGINT,

    link       TEXT,
    "order"    INT,

    PRIMARY KEY (id),
    CONSTRAINT auction_id_fk
        FOREIGN KEY (auction_id) REFERENCES auction (id)
);

CREATE TABLE parameter
(
    id  BIGINT,
    key TEXT UNIQUE,
--     count of appearances
    PRIMARY KEY (id)
);

CREATE TABLE auction_parameter
(
    auction_id   BIGINT,
    parameter_id BIGINT,
    value        TEXT,

    PRIMARY KEY (auction_id, parameter_id),
    CONSTRAINT auction_id_fk
        FOREIGN KEY (auction_id) REFERENCES auction (id),
    CONSTRAINT parameter_key_fk
        FOREIGN KEY (parameter_id) REFERENCES parameter (id)
);


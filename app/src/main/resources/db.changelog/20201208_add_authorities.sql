CREATE TABLE authorities(
    id BIGINT NOT NULL,
    authorities VARCHAR(255),
    FOREIGN KEY (id) references users (id)
);


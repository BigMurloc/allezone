CREATE TABLE app_user_authority (
    app_user_id BIGINT NOT NULL,
    authority VARCHAR(255),
    FOREIGN KEY (app_user_id) references app_user (id),
    FOREIGN KEY (authority) references app_authority(authority)
);


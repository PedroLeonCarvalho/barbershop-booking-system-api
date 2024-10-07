CREATE TABLE roles (
    id bigint not null auto_increment,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE privileges (
    id bigint not null auto_increment,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);
CREATE TABLE user (
    id bigint not null auto_increment,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    phoneNumber varchar(15),
    birthDate DATE,
    password varchar(255) NOT NULL,
    primary key (id)
);
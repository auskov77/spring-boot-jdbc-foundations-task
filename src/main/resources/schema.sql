DROP TABLE IF EXISTS customers, pets;

CREATE TABLE customers(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(256),
    email VARCHAR(256),
    animal VARCHAR(256),
    pet_id BIGINT
);

CREATE TABLE pets(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    breed VARCHAR(256)
);

ALTER TABLE customers add foreign key (pet_id) references pets(id);
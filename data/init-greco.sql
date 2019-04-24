CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  firstname VARCHAR (50) NOT NULL,
  lastname VARCHAR (50) NOT NULL,
  birthdate TIMESTAMP
);

INSERT INTO users (id, firstname, lastname, birthdate) VALUES(1, 'remy', 'trompier', null);
INSERT INTO users (id, firstname, lastname, birthdate) VALUES(2, 'guerric', 'merle', null);
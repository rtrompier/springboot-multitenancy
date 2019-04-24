CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  firstname VARCHAR (50) NOT NULL,
  lastname VARCHAR (50) NOT NULL,
  birthdate TIMESTAMP NOT NULL
);

INSERT INTO users (id, firstname, lastname, birthdate) VALUES(1, 'remy', 'trompier', to_timestamp('20-03-1990 00:00:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO users (id, firstname, lastname, birthdate) VALUES(2, 'guerric', 'merle', to_timestamp('01-01-1983 00:00:00', 'dd-mm-yyyy hh24:mi:ss'));
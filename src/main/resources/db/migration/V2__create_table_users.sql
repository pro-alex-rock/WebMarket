CREATE TABLE users
(
    id serial PRIMARY KEY,
    username  VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(250) NOT NULL,
    sole VARCHAR(250) NOT NULL
);
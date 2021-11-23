DROP TABLE products IF EXISTS;

create table products (
                          id SERIAL primary key,
                          name VARCHAR (250) UNIQUE NOT NULL,
                          price DECIMAL NOT NULL
);
CREATE SEQUENCE table_id_seq;
ALTER TABLE products
    ALTER COLUMN id
        SET DEFAULT NEXTVAL('table_id_seq');
INSERT INTO products VALUES ('IPhone', 1200);
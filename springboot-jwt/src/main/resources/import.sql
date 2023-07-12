INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-02', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('John', 'Roe', 'john.roe@gmail.com', '2017-08-13', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('John', 'Smith', 'john.smith@gmail.com', '2017-08-22', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25', '');

INSERT INTO products (name, price, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO products (name, price, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Mica Cómoda 5 Cajones', 299990, NOW());

INSERT INTO invoices (description, observations, customer_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES(1, 1, 1);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES(2, 1, 4);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES(1, 1, 5);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES(1, 1, 7);

INSERT INTO invoices (description, observations, customer_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES(3, 2, 6);

INSERT INTO users (username, password, enabled) VALUES('roberto', '$2a$10$Hf9eKwgKUVexBywXH4C5Zew6MPzl9xqVoo2yrgIQSb50QZP02FiC.', 1);
INSERT INTO users (username, password, enabled) VALUES('admin', '$2a$10$NTLzJPuI8a/UZd9jnAqlCOrc6Z30MzgxxFlMGMANWOtwS7Yn1YOZm', 1);

INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_ADMIN');
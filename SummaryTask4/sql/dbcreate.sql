CREATE DATABASE IF NOT EXISTS rentcardb;
USE rentcardb;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS bills;
DROP TABLE IF EXISTS statuses;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS brands;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS fuel;

CREATE TABLE IF NOT EXISTS roles (
    id INT unsigned NOT NULL PRIMARY KEY,
    role_name VARCHAR(20) NOT NULL UNIQUE
) ENGINE=InnoDB CHARACTER SET=UTF8;

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'manager');
INSERT INTO roles VALUES(2, 'client');

CREATE TABLE IF NOT EXISTS users (
    id INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(20) NOT NULL UNIQUE,    
    password VARCHAR(50) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
    role_id INT unsigned,
    block tinyint(1),
    FOREIGN KEY (role_id) REFERENCES roles(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB CHARACTER SET=UTF8;

INSERT INTO users VALUES(NULL, 'admin', 'admin', 'Ivan', 'Ivanov', 0, false);
INSERT INTO users VALUES(NULL, 'manager', 'manager', 'Dima', 'Dmitriev', 1, false);
INSERT INTO users VALUES(NULL, 'client', 'client', 'Анна', 'Антонова', 2, false);

CREATE TABLE IF NOT EXISTS brands (
    id INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
) ENGINE=InnoDB CHARACTER SET=UTF8;

INSERT INTO brands VALUES(NULL, 'AUDI');
INSERT INTO brands VALUES(NULL, 'FORD');
INSERT INTO brands VALUES(NULL, 'OPEL');
INSERT INTO brands VALUES(NULL, 'BMW');
INSERT INTO brands VALUES(NULL, 'TOYOTA');
INSERT INTO brands VALUES(NULL, 'HONDA');
INSERT INTO brands VALUES(NULL, 'DODGE');
INSERT INTO brands VALUES(NULL, 'JEEP');

CREATE TABLE IF NOT EXISTS categories (
    id INT unsigned NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
) ENGINE=InnoDB CHARACTER SET=UTF8;

INSERT INTO categories VALUES(0, 'Small car');
INSERT INTO categories VALUES(1, 'Medium car');
INSERT INTO categories VALUES(2, 'Large car');
INSERT INTO categories VALUES(3, 'Estate car');
INSERT INTO categories VALUES(4, 'Premium car');
INSERT INTO categories VALUES(5, 'People carriers');
INSERT INTO categories VALUES(6, 'SUVs');

CREATE TABLE IF NOT EXISTS fuel (
    id INT unsigned NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
) ENGINE=InnoDB CHARACTER SET=UTF8;

INSERT INTO fuel VALUES(0, 'petrol');
INSERT INTO fuel VALUES(1, 'diesel');
INSERT INTO fuel VALUES(2, 'gas');
INSERT INTO fuel VALUES(3, 'hybrid');
INSERT INTO fuel VALUES(4, 'electro');

CREATE TABLE IF NOT EXISTS cars (
    id INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
    brand_id INT unsigned NOT NULL,
    model VARCHAR(20) NOT NULL,
    category_id INT unsigned NOT NULL,
    seat_amount INT unsigned NOT NULL,
    fuel_id INT unsigned NOT NULL,
    air_condition TINYINT(1),
    automatic_transmission TINYINT(1),
    price INT unsigned NOT NULL,
    guarantee_amount INT unsigned NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brands(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
     ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (fuel_id) REFERENCES fuel(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB CHARACTER SET=UTF8;

INSERT INTO cars VALUES(NULL, 1, 'A8', 4, 4, 1, true, true, 200, 8000);
INSERT INTO cars VALUES(NULL, 2, 'Focus', 1, 4, 0, false, true, 80, 5000);
INSERT INTO cars VALUES(NULL, 3, 'Astra', 2, 4, 3, true, false, 100, 3000);

CREATE TABLE IF NOT EXISTS statuses (
	id INT unsigned NOT NULL PRIMARY KEY,
	name VARCHAR(20) NOT NULL
)ENGINE=InnoDB CHARACTER SET=UTF8;

INSERT INTO statuses VALUES(0, 'opened');
INSERT INTO statuses VALUES(1, 'confirmed');
INSERT INTO statuses VALUES(2, 'canceled');
INSERT INTO statuses VALUES(3, 'paid');
INSERT INTO statuses VALUES(4, 'complained');
INSERT INTO statuses VALUES(5, 'closed');

CREATE TABLE IF NOT EXISTS bills (
id INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_id INT unsigned NOT NULL,
operation_name VARCHAR(10) NOT NULL,
amount INT unsigned NOT NULL,
state TINYINT(1),
FOREIGN KEY (user_id) REFERENCES users(id)
)ENGINE=InnoDB CHARACTER SET=UTF8;

CREATE TABLE IF NOT EXISTS orders (
    id INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bill INT unsigned,
    car_id INT unsigned NOT NULL,
    user_id INT unsigned NOT NULL,
    passport VARCHAR(10) NOT NULL,
    driver TINYINT(1),
    price INT unsigned NOT NULL,
    period INT unsigned NOT NULL,
    amount INT unsigned NOT NULL,
    status_id INT unsigned NOT NULL,
    compensation_sum INT unsigned,
    comments VARCHAR(40), 
    FOREIGN KEY (status_id) REFERENCES statuses(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (car_id) REFERENCES cars(id),
	FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB CHARACTER SET=UTF8;


CREATE DATABASE IF NOT EXISTS fleetdb;
USE fleetdb;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', '1234');

DROP TABLE IF EXISTS vehicles;
CREATE TABLE vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(20),   -- 'Car' or 'Truck'
    model VARCHAR(50),
    plate VARCHAR(20) UNIQUE,
    seats INT NULL,
    capacity DOUBLE NULL
);

INSERT INTO vehicles (type, model, plate, seats, capacity) VALUES
('Car', 'Toyota Corolla', 'KAA123', 5, NULL),
('Car', 'Honda Civic', 'KBZ789', 4, NULL),
('Truck', 'Mercedes Actros', 'KBX456', NULL, 18.0);

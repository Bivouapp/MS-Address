-- Création de la table addresses
CREATE TABLE addresses (
        address_id serial PRIMARY KEY,
        number varchar(10),
        street varchar(100),
        city varchar(50),
        postal_code varchar(20),
        latitude double precision,
        longitude double precision
);

-- Insertion de données dans la table addresses
INSERT INTO addresses(number, street, city, postal_code, latitude, longitude)
VALUES
    ('123', 'Main Street', 'Springfield', '12345', 37.7749, -122.4194),
    ('456', 'Broadway', 'New York', '10001', 40.7128, -74.0060);
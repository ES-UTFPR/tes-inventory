CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    notes TEXT
);
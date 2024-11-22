CREATE TABLE banks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255)
);

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE bank_accounts (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(255) NOT NULL,
    name_account VARCHAR(255),
    bank_id INT REFERENCES banks(id),
    currency VARCHAR(3) NOT NULL,
    initial_balance NUMERIC NOT NULL,
    user_id INT REFERENCES users(id)
);

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    account_id INT REFERENCES bank_accounts(id),
    name_account VARCHAR(255),
    amount NUMERIC NOT NULL,
    currency VARCHAR(3) NOT NULL,
    type VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    date TIMESTAMP,
    category_id INT REFERENCES categories(id),
    notes TEXT
);

CREATE TABLE income_source (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE income_record (
    id SERIAL PRIMARY KEY,
    income_source_id INT REFERENCES income_source(id),
    amount NUMERIC NOT NULL,
    date DATE NOT NULL
);

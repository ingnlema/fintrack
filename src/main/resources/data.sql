-- Insertar bancos
INSERT INTO banks (id, name, country) VALUES
    (1, 'Santander', 'Uruguay'),
    (2, 'Itaú', 'Uruguay'),
    (3, 'BROU', 'Uruguay');

-- Insertar usuarios
INSERT INTO users (id, username, email) VALUES
    (1, 'nicolas', 'nicolas@example.com'),
    (2, 'juan', 'juan@example.com'),
    (3, 'felipe', 'felipe@example.com');

-- Insertar categorías
INSERT INTO categories (id, name) VALUES
    (1, 'Supermercado'),
    (2, 'Entretenimiento'),
    (3, 'Servicios');

-- Insertar cuentas bancarias
INSERT INTO bank_accounts (id, account_number, name_account, bank_id, currency, initial_balance, user_id) VALUES
    (1, '1234567890', 'Cuenta Nicolás', 1, 'UYU', 10000.0, 1),
    (2, '9876543210', 'Cuenta Juan', 2, 'USD', 5000.0, 2),
    (3, '4567891230', 'Cuenta Felipe', 3, 'UYU', 20000.0, 3);

-- Insertar transacciones
INSERT INTO transactions (id, account_id, name_account, amount, currency, type, description, date, category_id, notes) VALUES
    (1, 1, 'Cuenta Nicolás', 1500.0, 'UYU', 'EXPENSE', 'Supermercado', '2024-11-19T00:00:00', 1, null),
    (2, 1, 'Cuenta Nicolás', 800.0, 'UYU', 'EXPENSE', 'Cine', '2024-11-20T00:00:00', 2, null),
    (3, 2, 'Cuenta Juan', 2500.0, 'USD', 'INCOME', 'Sueldo', '2024-11-10T00:00:00', null, null),
    (4, 3, 'Cuenta Felipe', 1200.0, 'UYU', 'EXPENSE', 'Pago de internet', '2024-11-14T00:00:00', 3, null),
    (5, 3, 'Cuenta Felipe', 3000.0, 'UYU', 'EXPENSE', 'Cena', '2024-11-18T00:00:00', 2, null),
    (6, 1, 'Cuenta Nicolás', 5000.0, 'UYU', 'INCOME', 'Freelance', '2024-11-05T00:00:00', null, null);

-- Insertar fuentes de ingresos
INSERT INTO income_source (id, name) VALUES
    (1, 'Freelance'),
    (2, 'Sueldo');

-- Insertar registros de ingresos
INSERT INTO income_record (id, income_source_id, amount, date) VALUES
    (1, 1, 5000.0, '2024-11-05'),
    (2, 2, 2500.0, '2024-11-10');

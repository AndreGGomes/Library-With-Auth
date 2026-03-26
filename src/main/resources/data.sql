CREATE TABLE IF NOT EXISTS books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(255),
    author VARCHAR(255)
    );

-- Criar tabela de Usuários (Users)
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50)
    );

-- Inserindo um admin com a senha 'admin123' (hash BCrypt)
INSERT INTO users (name, password, role)
VALUES ('Admin', '$2a$12$jbbVsU6Pli/bbvnhV3xc6ujMBhWVGvLfPg3b/TrjvuRiFNbQfJE2e', 'ADMIN');

INSERT INTO books (title, isbn, author)
VALUES ('Clean Code', '978-0132350884', 'Robert C. Martin');
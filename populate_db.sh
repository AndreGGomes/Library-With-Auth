#!/bin/bash

CONTAINER_NAME="library-db"
DB_USER="admin"
DB_NAME="library_db"

ADMIN_PASS='$2a$12$xNxE0P5963lzKPi5wfUOCOxfn51aSm2r4Z4MNJlceT56u3sr0Hx9G'

docker exec -i $CONTAINER_NAME psql -U $DB_USER -d $DB_NAME <<'EOF'
-- Creating user table if it does not exists
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255)
);

-- Creating books table if it does not exists
CREATE TABLE IF NOT EXISTS books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    isbn VARCHAR(255)
);
EOF

docker exec -i $CONTAINER_NAME psql -U $DB_USER -d $DB_NAME <<EOF
-- Inserting admin
INSERT INTO users (username, password, role) 
VALUES ('Admin', '$ADMIN_PASS', 'ADMIN') 
ON CONFLICT (username) DO NOTHING;

-- Inserting example books
INSERT INTO books (title, author, isbn) VALUES ('Book1', 'Author1', '1111111111');
INSERT INTO books (title, author, isbn) VALUES ('Book2', 'Author2', '2222222222');
INSERT INTO books (title, author, isbn) VALUES ('Book3', 'Author3', '3333333333');
EOF


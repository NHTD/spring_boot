CREATE TABLE users (
   id UUID PRIMARY KEY,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   email VARCHAR(255),
   password VARCHAR(255),
   confirm_password VARCHAR(255),
   created_at TIMESTAMP,
   updated_at TIMESTAMP
);

CREATE TABLE roles (
   name VARCHAR(255) PRIMARY KEY,
   description VARCHAR(255)
);

CREATE TABLE user_roles (
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    role_name VARCHAR(255) REFERENCES roles(name) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_name)
);

CREATE TABLE categories (
    id BIGINT PRIMARY KEY,
    code VARCHAR(255),
    value VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE books (
    id BIGINT PRIMARY KEY,
    title VARCHAR(20) NOT NULL,
    image VARCHAR(255),
    description TEXT,
    category_id BIGINT,
    user_id UUID,
    status VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE SET NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL
);

CREATE TABLE transactions (
    id BIGINT PRIMARY KEY,
    user_id UUID,
    book_id BIGINT,
    borrow_date TIMESTAMP,
    return_date TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_user_transaction FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
    CONSTRAINT fk_book_transaction FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE SET NULL
);

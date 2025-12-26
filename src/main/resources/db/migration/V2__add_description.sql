ALTER TABLE products
    ADD `description` TEXT NULL;

ALTER TABLE products
    MODIFY `description` TEXT NOT NULL;

ALTER TABLE products
    MODIFY name VARCHAR (255) NULL;

ALTER TABLE products
    MODIFY price DECIMAL;

ALTER TABLE products
    MODIFY price DECIMAL NULL;
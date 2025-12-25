CREATE TABLE addresses
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    street  VARCHAR(255) NULL,
    city    VARCHAR(255) NULL,
    zip     VARCHAR(255) NULL,
    user_id BIGINT NULL,
    CONSTRAINT pk_addresses PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id   TINYINT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            INT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    price         DECIMAL NULL,
    category_id   TINYINT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE profiles
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    bio            VARCHAR(255) NULL,
    phone_number   VARCHAR(255) NULL,
    date_of_birth  datetime NULL,
    loyalty_points INT NULL,
    CONSTRAINT pk_profiles PRIMARY KEY (id)
);

CREATE TABLE tags
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_tags PRIMARY KEY (id)
);

CREATE TABLE user_tags
(
    tag_id  INT    NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_tags PRIMARY KEY (tag_id, user_id)
);

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE addresses
    ADD CONSTRAINT FK_ADDRESSES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE profiles
    ADD CONSTRAINT FK_PROFILES_ON_ID FOREIGN KEY (id) REFERENCES users (id);

ALTER TABLE user_tags
    ADD CONSTRAINT fk_user_tags_on_tag FOREIGN KEY (tag_id) REFERENCES tags (id);

ALTER TABLE user_tags
    ADD CONSTRAINT fk_user_tags_on_user FOREIGN KEY (user_id) REFERENCES users (id);
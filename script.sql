create table categories
(
    id   tinyint auto_increment
        primary key,
    name varchar(255) not null
);

create table flyway_schema_history
(
    installed_rank int                                 not null
        primary key,
    version        varchar(50)                         null,
    description    varchar(200)                        not null,
    type           varchar(20)                         not null,
    script         varchar(1000)                       not null,
    checksum       int                                 null,
    installed_by   varchar(100)                        not null,
    installed_on   timestamp default CURRENT_TIMESTAMP not null,
    execution_time int                                 not null,
    success        tinyint(1)                          not null
);

create index flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table products
(
    id          int auto_increment
        primary key,
    name        varchar(255) null,
    price       decimal      null,
    category_id tinyint      null,
    description text         not null,
    constraint products_categories_id_fk
        foreign key (category_id) references categories (id)
);

create table profiles
(
    id             bigint auto_increment
        primary key,
    bio            varchar(255) not null,
    phone_number   int          not null,
    date_of_birth  varchar(255) not null,
    loyalty_points int          not null
);

create table tags
(
    id   int auto_increment
        primary key,
    name varchar(255) not null
);

create table users
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255) not null
);

create table addresses
(
    id      int auto_increment
        primary key,
    street  varchar(255) not null,
    city    varchar(255) not null,
    zip     varchar(255) not null,
    user_id bigint       not null,
    constraint addresses_users_id_fk
        foreign key (user_id) references users (id)
);

create table user_tags
(
    user_id bigint null,
    tag_id  int    null,
    constraint user_tags_tags_id_fk
        foreign key (tag_id) references tags (id),
    constraint user_tags_users_id_fk
        foreign key (user_id) references users (id)
);



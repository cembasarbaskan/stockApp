create table customer
(
    id            varchar(255) primary key,
    name          varchar(255) not null,
    last_name     varchar(255) not null,
    email         varchar(255) not null unique,
    address       varchar(255) not null,
    telephone     varchar(255) not null unique,
    year_of_birth integer      not null,

    change_time   date,
    create_time   date,
    create_user   varchar(255),
    change_user   varchar(255)
);

create table book
(
    id           varchar(255) primary key,
    name         varchar(255) not null,
    author       varchar(255) not null,
    publisher    varchar(255) not null,
    publish_date date         not null,
    stock_size   integer default 0,

    change_time  date,
    create_time  date,
    create_user  varchar(255),
    change_user  varchar(255),

    constraint uc_book unique (name, author, publisher)
);

create table book_genre
(
    book_id varchar(255) not null,
    genre   varchar(255),
    constraint book_genre_fk
        foreign key (book_id) references book (id)
);

create table order_table
(
    id          varchar(255) primary key,
    code        varchar(255) not null unique,
    customer_id varchar(255) not null,
    status      varchar(255) not null,

    change_time date,
    create_time date,
    create_user varchar(255),
    change_user varchar(255),

    foreign key (customer_id) references customer (id)
);

create table ordered_book
(
    book_id     VARCHAR(255) not null,
    order_code  varchar(255) not null,
    piece       integer      not null default 0,

    change_time date,
    create_time date,
    create_user varchar(255),
    change_user varchar(255),

    primary key (order_code, book_id),
    constraint ob_order_fk
        foreign key (order_code) references order_table (code),
    constraint ob_book_fk
        foreign key (book_id) references book (id)
);

create table stock
(
    id          varchar(255) primary key,
    book_id     varchar(255) not null unique,
    piece       integer      not null default 0,

    change_time date,
    create_time date,
    create_user varchar(255),
    change_user varchar(255)
);
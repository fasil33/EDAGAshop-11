

create table orders (
    id bigint not null auto_increment,
    address varchar(255),
    city varchar(255),
    date date,
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    phone_number varchar(255),
    post_index int4 not null check (post_index>=5),
    total_price float8,
    user_id int8,
    primary key (id)
);

create table orders_perfume_list (
    order_id int8 not null,
    perfume_list_id int8 not null,
    perfume_list_order int4 not null,
    primary key (order_id, perfume_list_order)
);

create table perfume (
    id int8 not null,
    country varchar(255),
    description varchar(255),
    filename varchar(255),
    fragrance_base_notes varchar(255),
    fragrance_middle_notes varchar(255),
    fragrance_top_notes varchar(255),
    perfume_gender varchar(255),
    perfume_title varchar(255),
    perfumer varchar(255),
    price int4 not null,
    type varchar(255),
    volume varchar(255),
    year int4 not null,
    primary key (id)
);

create table perfume_reviews (
    perfume_id int8 not null,
    reviews_id int8 not null
);

create table review (
    id int8 not null,
    author varchar(255),
    date date,
    message varchar(255),
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    activation_code varchar(255),
    password_reset_code varchar(255),
    active boolean not null,
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

create table usr_perfume_list (
    user_id int8 not null,
    perfume_list_id int8 not null
);

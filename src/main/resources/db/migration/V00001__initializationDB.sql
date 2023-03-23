create sequence orders_id_seq1;

alter sequence orders_id_seq1 owner to postgres;

create table if not exists storages
(
    id      bigserial
        constraint storages_pk
            primary key,
    name    varchar,
    created timestamp default now(),
    changed timestamp default now()
);

alter table storages
    owner to postgres;

create table if not exists manufacturers
(
    id      bigserial
        constraint manufacturers_pk
            primary key,
    name    varchar,
    created timestamp default now(),
    changed timestamp default now()
);

alter table manufacturers
    owner to postgres;

create table if not exists customers
(
    id              bigserial
        constraint customers_pk
            primary key,
    first_name      varchar,
    last_name       varchar,
    address         varchar,
    phone           varchar,
    email           varchar,
    purchase_amount double precision default 0,
    login           varchar,
    password        varchar,
    created         timestamp        default now(),
    changed         timestamp        default now(),
    is_deleted      boolean          default false
);

alter table customers
    owner to postgres;

create table if not exists products
(
    id              bigserial
        constraint products_pk
            primary key,
    kind            varchar,
    topping         varchar,
    manufacturer_id bigint
        constraint products_manufacturers_id_fk
            references manufacturers,
    name            varchar,
    description     varchar,
    weight          integer,
    price           double precision,
    created         timestamp default now(),
    changed         timestamp default now()
);

alter table products
    owner to postgres;

create table if not exists l_storages_products_value
(
    storage_id bigint
        constraint l_storages_products_value_storages_id_fk
            references storages,
    product_id bigint
        constraint l_storages_products_value_products_id_fk
            references products,
    quantity   integer,
    id         bigserial
        constraint l_storages_products_value_pk
            primary key,
    created    timestamp default now(),
    changed    timestamp default now()
);

alter table l_storages_products_value
    owner to postgres;

create table if not exists orders
(
    id           bigint    default nextval('orders_id_seq1'::regclass) not null
        constraint orders_pk
            primary key,
    order_number bigint,
    product_id   bigint
        constraint orders_products_id_fk
            references products,
    customer_id  bigint
        constraint orders_customers_id_fk
            references customers,
    quantity     integer,
    created      timestamp default now(),
    changed      timestamp default now(),
    cancelled    boolean   default false,
    finished     boolean   default false
);

alter table orders
    owner to postgres;

alter sequence orders_id_seq1 owned by orders.id;
create sequence orders_id_seq1;

alter sequence orders_id_seq1 owner to postgres;

create table if not exists flyway_schema_history
(
    installed_rank integer                 not null
    constraint flyway_schema_history_pk
    primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
    );

alter table flyway_schema_history
    owner to postgres;

create index if not exists flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table if not exists manufacturers
(
    id      bigserial
    constraint manufacturers_pk
    primary key,
    name    varchar(50),
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
    first_name      varchar(50),
    last_name       varchar(50),
    address         varchar(250),
    phone           varchar(50),
    email           varchar(250),
    purchase_amount double precision default 0,
    login           varchar(200),
    password        varchar(1000),
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
    kind            varchar(50),
    topping         varchar(50),
    manufacturer_id bigint
    constraint products_manufacturers_id_fk
    references manufacturers
    on update cascade on delete cascade,
    name            varchar(50),
    description     varchar(500),
    weight          integer,
    price           double precision,
    created         timestamp default now(),
    changed         timestamp default now()
    );

alter table products
    owner to postgres;

create table if not exists orders
(
    id           bigint    default nextval('orders_id_seq1'::regclass) not null
    constraint orders_pk
    primary key,
    order_number bigint,
    product_id   bigint
    constraint orders_products_id_fk
    references products
    on update cascade on delete cascade,
    customer_id  bigint
    constraint orders_customers_id_fk
    references customers
    on update cascade on delete cascade,
    quantity     integer,
    created      timestamp default now(),
    changed      timestamp default now(),
    cancelled    boolean   default false,
    collected    boolean   default false,
    finished     boolean   default false
    );

alter table orders
    owner to postgres;

alter sequence orders_id_seq1 owned by orders.id;

create table if not exists roles
(
    id          bigserial,
    customer_id bigint
    constraint roles_customers_id_fk
    references customers,
    roles       varchar(50)   default 'USER'::character varying not null,
    created     timestamp default now(),
    changed     timestamp default now()
    );

alter table roles
    owner to postgres;
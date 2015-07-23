create table o_address (
  id                        bigserial not null,
  line1                     varchar(100),
  line2                     varchar(100),
  city                      varchar(100),
  country_code              varchar(2),
  version                   bigint not null,
  when_created              timestamp not null,
  when_updated              timestamp not null,
  constraint pk_o_address primary key (id))
;

create table contact (
  id                        bigserial not null,
  customer_id               bigint not null,
  first_name                varchar(50),
  last_name                 varchar(50),
  email                     varchar(200),
  phone                     varchar(20),
  version                   bigint not null,
  when_created              timestamp not null,
  when_updated              timestamp not null,
  constraint pk_contact primary key (id))
;

create table country (
  code                      varchar(2) not null,
  name                      varchar(60),
  constraint pk_country primary key (code))
;

create table customer (
  id                        bigserial not null,
  name                      varchar(100),
  registered                date,
  comments                  varchar(1000),
  billing_address_id        bigint,
  shipping_address_id       bigint,
  version                   bigint not null,
  when_created              timestamp not null,
  when_updated              timestamp not null,
  constraint pk_customer primary key (id))
;

create table feature (
  id                        bigserial not null,
  name                      varchar(60),
  version                   bigint not null,
  when_created              timestamp not null,
  when_updated              timestamp not null,
  constraint pk_feature primary key (id))
;

create table product (
  id                        bigserial not null,
  sku                       varchar(20),
  name                      varchar(255),
  version                   bigint not null,
  when_created              timestamp not null,
  when_updated              timestamp not null,
  constraint pk_product primary key (id))
;


create table customer_feature (
  customer_id                    bigint not null,
  feature_id                     bigint not null,
  constraint pk_customer_feature primary key (customer_id, feature_id))
;
alter table o_address add constraint fk_o_address_country_1 foreign key (country_code) references country (code);
create index ix_o_address_country_1 on o_address (country_code);
alter table contact add constraint fk_contact_customer_2 foreign key (customer_id) references customer (id);
create index ix_contact_customer_2 on contact (customer_id);
alter table customer add constraint fk_customer_billingAddress_3 foreign key (billing_address_id) references o_address (id);
create index ix_customer_billingAddress_3 on customer (billing_address_id);
alter table customer add constraint fk_customer_shippingAddress_4 foreign key (shipping_address_id) references o_address (id);
create index ix_customer_shippingAddress_4 on customer (shipping_address_id);



alter table customer_feature add constraint fk_customer_feature_customer_01 foreign key (customer_id) references customer (id);

alter table customer_feature add constraint fk_customer_feature_feature_02 foreign key (feature_id) references feature (id);

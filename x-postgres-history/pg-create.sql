create table customer (
  id                        bigserial not null,
  inactive                  boolean,
  name                      varchar(100),
  registered                date,
  comments                  varchar(1000),
  version                   bigint not null,
  when_created              timestamp not null,
  when_updated              timestamp not null,
  constraint pk_customer primary key (id))
;

create table o_product (
  id                        bigserial not null,
  sku                       varchar(20),
  name                      varchar(255),
  version                   bigint not null,
  when_created              timestamp not null,
  when_updated              timestamp not null,
  constraint pk_o_product primary key (id))
;




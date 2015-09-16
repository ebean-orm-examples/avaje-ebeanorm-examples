create table customer (
  id                            bigint not null,
  name                          varchar(50) not null,
  short_desc                    varchar(255),
  when_created                  timestamp not null,
  when_modified                 timestamp not null,
  version                       bigint not null,
  constraint pk_customer primary key (id)
);
create sequence customer_seq;


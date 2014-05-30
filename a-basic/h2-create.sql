create table be_contact (
  id                        bigint not null,
  first_name                varchar(50),
  last_name                 varchar(50),
  email                     varchar(200),
  phone                     varchar(20),
  customer_id               bigint not null,
  version                   bigint not null,
  when_created              timestamp not null,
  when_updated              timestamp not null,
  constraint pk_be_contact primary key (id))
;

create table be_customer (
  id                        bigint not null,
  name                      varchar(100),
  registered                timestamp,
  comments                  varchar(1000),
  version                   bigint not null,
  when_created              timestamp not null,
  when_updated              timestamp not null,
  constraint pk_be_customer primary key (id))
;

create sequence be_contact_seq;

create sequence be_customer_seq;

alter table be_contact add constraint fk_be_contact_customer_1 foreign key (customer_id) references be_customer (id) on delete restrict on update restrict;
create index ix_be_contact_customer_1 on be_contact (customer_id);



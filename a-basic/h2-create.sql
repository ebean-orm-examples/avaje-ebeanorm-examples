create table be_customer (
  id                        bigint not null,
  name                      varchar(255),
  registered                timestamp,
  comments                  varchar(255),
  version                   bigint not null,
  when_created              timestamp not null,
  when_updated              timestamp not null,
  constraint pk_be_customer primary key (id))
;

create sequence be_customer_seq;




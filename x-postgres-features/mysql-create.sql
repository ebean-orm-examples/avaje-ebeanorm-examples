create table p_customer (
  id                        bigint auto_increment not null,
  inactive                  tinyint(1) default 0,
  name                      varchar(100),
  registered                datetime,
  comments                  varchar(1000),
  version                   bigint not null,
  when_created              datetime not null,
  when_updated              datetime not null,
  constraint pk_p_customer primary key (id))
;




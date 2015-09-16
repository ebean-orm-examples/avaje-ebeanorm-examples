create table p_customer (
  id                            bigserial not null,
  inactive                      boolean,
  name                          varchar(100),
  registered                    timestamp,
  comments                      varchar(1000),
  tags                          hstore,
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_p_customer primary key (id)
);

create table p_doc (
  id                            bigserial not null,
  name                          varchar(255),
  content                       jsonb,
  version                       bigint not null,
  constraint pk_p_doc primary key (id)
);

create table p_doc_jsonnode (
  id                            bigserial not null,
  name                          varchar(255),
  content                       jsonb,
  version                       bigint not null,
  constraint pk_p_doc_jsonnode primary key (id)
);

create table p_user (
  user_id                       bigserial not null,
  name                          varchar(255),
  constraint pk_p_user primary key (user_id)
);


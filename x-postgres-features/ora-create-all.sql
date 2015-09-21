create table p_customer (
  id                            number(19) not null,
  inactive                      number(1),
  name                          varchar2(100),
  registered                    timestamp,
  comments                      varchar2(1000),
  tags                          hstore,
  version                       number(19) not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_p_customer primary key (id)
);
create sequence p_customer_seq;

create table p_doc (
  id                            number(19) not null,
  name                          varchar2(255),
  content                       clob,
  version                       number(19) not null,
  constraint pk_p_doc primary key (id)
);
create sequence p_doc_seq;

create table p_doc_jsonnode (
  id                            number(19) not null,
  name                          varchar2(255),
  content                       clob,
  version                       number(19) not null,
  constraint pk_p_doc_jsonnode primary key (id)
);
create sequence p_doc_jsonnode_seq;

create table p_user (
  user_id                       number(19) not null,
  name                          varchar2(255),
  constraint pk_p_user primary key (user_id)
);
create sequence p_user_seq;


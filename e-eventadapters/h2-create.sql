create table e_user (
  id                        varchar(40) not null,
  creator_id                varchar(255),
  creator_name              varchar(255),
  modifier_id               varchar(255),
  modifier_name             varchar(255),
  name                      varchar(255),
  age                       integer,
  create_time               timestamp not null,
  modify_time               timestamp not null,
  constraint pk_e_user primary key (id))
;




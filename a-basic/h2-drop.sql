SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists be_contact;

drop table if exists be_customer;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists be_contact_seq;

drop sequence if exists be_customer_seq;


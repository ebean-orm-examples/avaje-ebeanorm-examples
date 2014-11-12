SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists o_address;

drop table if exists be_contact;

drop table if exists contact_note;

drop table if exists o_country;

drop table if exists be_customer;

drop table if exists o_order;

drop table if exists o_order_detail;

drop table if exists o_product;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists o_address_seq;

drop sequence if exists be_contact_seq;

drop sequence if exists contact_note_seq;

drop sequence if exists o_country_seq;

drop sequence if exists be_customer_seq;

drop sequence if exists o_order_seq;

drop sequence if exists o_order_detail_seq;

drop sequence if exists o_product_seq;


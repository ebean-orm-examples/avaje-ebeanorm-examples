alter table o_address drop constraint if exists fk_o_address_country_code;
drop index if exists ix_o_address_country_code;

alter table be_contact drop constraint if exists fk_be_contact_customer_id;
drop index if exists ix_be_contact_customer_id;

alter table contact_note drop constraint if exists fk_contact_note_contact_id;
drop index if exists ix_contact_note_contact_id;

alter table be_customer drop constraint if exists fk_be_customer_billing_address_id;
drop index if exists ix_be_customer_billing_address_id;

alter table be_customer drop constraint if exists fk_be_customer_shipping_address_id;
drop index if exists ix_be_customer_shipping_address_id;

alter table o_order drop constraint if exists fk_o_order_customer_id;
drop index if exists ix_o_order_customer_id;

alter table o_order drop constraint if exists fk_o_order_shipping_address_id;
drop index if exists ix_o_order_shipping_address_id;

alter table o_order_detail drop constraint if exists fk_o_order_detail_order_id;
drop index if exists ix_o_order_detail_order_id;

alter table o_order_detail drop constraint if exists fk_o_order_detail_product_id;
drop index if exists ix_o_order_detail_product_id;

drop table if exists o_address;
drop sequence if exists o_address_seq;

drop table if exists be_contact;
drop sequence if exists be_contact_seq;

drop table if exists contact_note;
drop sequence if exists contact_note_seq;

drop table if exists o_country;

drop table if exists be_customer;
drop sequence if exists be_customer_seq;

drop table if exists o_order;
drop sequence if exists o_order_seq;

drop table if exists o_order_detail;
drop sequence if exists o_order_detail_seq;

drop table if exists o_product;
drop sequence if exists o_product_seq;


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

drop table if exists o_address cascade;

drop table if exists be_contact cascade;

drop table if exists contact_note cascade;

drop table if exists o_country cascade;

drop table if exists be_customer cascade;

drop table if exists o_order cascade;

drop table if exists o_order_detail cascade;

drop table if exists o_product cascade;


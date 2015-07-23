ALTER TABLE customer ADD COLUMN sys_period tstzrange NOT NULL;

CREATE TABLE customer_history (LIKE customer);

CREATE VIEW customer_with_history AS SELECT * FROM customer UNION ALL SELECT * FROM customer_history;


CREATE OR REPLACE FUNCTION customer_history_version() RETURNS TRIGGER AS $$
BEGIN

  IF (TG_OP = 'INSERT') THEN
    NEW.sys_period = tstzrange(CURRENT_TIMESTAMP,null);
    RETURN NEW;

  ELSIF (TG_OP = 'UPDATE') THEN
    insert into customer_history (sys_period, id, inactive, name, comments, billing_address_id, shipping_address_id, version, when_created, when_updated)
    VALUES (tstzrange(lower(OLD.sys_period), CURRENT_TIMESTAMP), OLD.id, OLD.inactive, OLD.name, OLD.comments, OLD.billing_address_id, OLD.shipping_address_id, OLD.version, OLD.when_created, OLD.when_updated);
    NEW.sys_period = tstzrange(CURRENT_TIMESTAMP,null);
    RETURN NEW;

  ELSEIF (TG_OP = 'DELETE') THEN
    insert into customer_history (sys_period, id, inactive, name, comments, billing_address_id, shipping_address_id, version, when_created, when_updated)
    VALUES (tstzrange(lower(OLD.sys_period), CURRENT_TIMESTAMP), OLD.id, OLD.inactive, OLD.name, OLD.comments, OLD.billing_address_id, OLD.shipping_address_id, OLD.version, OLD.when_created, OLD.when_updated);
    RETURN OLD;

  END IF;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER customer_versioning_upd
BEFORE INSERT OR UPDATE OR DELETE ON customer
FOR EACH ROW EXECUTE PROCEDURE customer_history_version();





select * from customer;
select * from customer_history;


insert into customer (name, comments, version, when_created, when_updated)
values ('jack','jack 1',1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into customer (name, comments, version, when_created, when_updated)
    values ('jill','jill 1',1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

update customer set comments = 'jack 2' where id = 1;
update customer set comments = 'jack 3' where id = 1;
select sys_period, * from customer_history;

select sys_period, * from customer;
select sys_period, * from customer_history;

select sys_period, * from o_address;
update customer set billing_address_id = 1 where id = 1;

delete from customer where id = 5;



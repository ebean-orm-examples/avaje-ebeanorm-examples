ALTER TABLE customer ADD COLUMN sys_period tstzrange NOT NULL;


CREATE TABLE customer_history (LIKE employees);


CREATE TRIGGER customer_versioning_trigger
BEFORE INSERT OR UPDATE OR DELETE ON customer
FOR EACH ROW EXECUTE PROCEDURE versioning('sys_period',
                                          'customer_history',
                                          true);


CREATE TRIGGER customer_versioning_upd
BEFORE UPDATE ON customer
FOR EACH ROW EXECUTE PROCEDURE cust_update();

CREATE TRIGGER customer_versioning_ins
BEFORE INSERT ON customer
FOR EACH ROW EXECUTE PROCEDURE cust_insert();

CREATE TRIGGER customer_versioning_del
BEFORE DELETE ON customer
FOR EACH ROW EXECUTE PROCEDURE cust_delete();

drop TRIGGER customer_versioning_del on customer;

create or REPLACE FUNCTION cust_update() RETURNS TRIGGER AS $cust_update$
BEGIN
  insert into customer_history (sys_period,
                                id, inactive, name, comments, version, when_created, when_updated)
  VALUES (tstzrange(lower(OLD.sys_period), CURRENT_TIMESTAMP),
          OLD.id, OLD.inactive, OLD.name, OLD.comments, OLD.version, OLD.when_created, OLD.when_updated);
  NEW.sys_period = tstzrange(CURRENT_TIMESTAMP,null);
  RETURN NEW;
END;
$cust_update$ LANGUAGE plpgsql;

create or REPLACE FUNCTION cust_insert() RETURNS TRIGGER AS $$
BEGIN
  NEW.sys_period = tstzrange(CURRENT_TIMESTAMP,null);
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE VIEW customer_with_history AS
    SELECT * FROM customer
  UNION ALL
    SELECT * FROM customer_history;

create or REPLACE FUNCTION cust_update() RETURNS TRIGGER AS $$
BEGIN
  insert into customer_history (sys_period,
                                id, inactive, name, comments, version, when_created, when_updated)
  VALUES (tstzrange(lower(OLD.sys_period), CURRENT_TIMESTAMP),
          OLD.id, OLD.inactive, OLD.name, OLD.comments, OLD.version, OLD.when_created, OLD.when_updated);
  NEW.sys_period = tstzrange(CURRENT_TIMESTAMP,null);
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

insert into customer (name, comments, version, when_created, when_updated)
    values ('jack','jack 1',1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

select sys_period, * from customer;
select sys_period, * from customer_history;
delete from customer where id = 5;


update customer set comments = 'rob update' where id = 1;
select sys_period, * from customer_history;


create or REPLACE FUNCTION cust_delete() RETURNS TRIGGER AS $$
BEGIN
  insert into customer_history (sys_period,
                                id, inactive, name, comments, version, when_created, when_updated)
  VALUES (tstzrange(lower(OLD.sys_period), CURRENT_TIMESTAMP),
          OLD.id, OLD.inactive, OLD.name, OLD.comments, OLD.version, OLD.when_created, OLD.when_updated);
  RETURN OLD;
END;
$$ LANGUAGE plpgsql;
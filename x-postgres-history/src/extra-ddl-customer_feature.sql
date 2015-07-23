ALTER TABLE customer_feature ADD COLUMN sys_period tstzrange NOT NULL;

CREATE TABLE customer_feature_history (LIKE customer_feature);

CREATE VIEW customer_feature_with_history AS SELECT * FROM customer_feature UNION ALL SELECT * FROM customer_feature_history;




CREATE OR REPLACE FUNCTION customer_feature_history_version() RETURNS TRIGGER AS $$
BEGIN

  IF (TG_OP = 'INSERT') THEN
    NEW.sys_period = tstzrange(CURRENT_TIMESTAMP,null);
    RETURN NEW;

  ELSIF (TG_OP = 'UPDATE') THEN
    insert into customer_feature_history (sys_period, customer_id, feature_id)
    VALUES (tstzrange(lower(OLD.sys_period), CURRENT_TIMESTAMP), OLD.customer_id, OLD.feature_id);
    NEW.sys_period = tstzrange(CURRENT_TIMESTAMP,null);
    RETURN NEW;

  ELSEIF (TG_OP = 'DELETE') THEN
    insert into customer_feature_history (sys_period, customer_id, feature_id)
    VALUES (tstzrange(lower(OLD.sys_period), CURRENT_TIMESTAMP), OLD.customer_id, OLD.feature_id);
    RETURN OLD;

  END IF;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER customer_feature_versioning_upd
BEFORE INSERT OR UPDATE OR DELETE ON customer_feature
FOR EACH ROW EXECUTE PROCEDURE customer_feature_history_version();

--
-- END --------------------------
--

select * from feature;

insert into feature (name, version, when_created, when_updated) VALUES ('green', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into feature (name, version, when_created, when_updated) VALUES ('red', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into feature (name, version, when_created, when_updated) VALUES ('blue', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

select * from customer_feature;
select * from customer_feature_history;
select * from customer_feature_with_history;

insert into o_address (line1, city, country_code, version, when_created, when_updated)
values ('9 Queen St','Auckland','NZ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into o_address (line1, city, country_code, version, when_created, when_updated)
values ('23 Bee St','Wellington','NZ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


update o_address set line1 = '42 Good luck' where id = 1;
update o_address set line2 = 'central' where id = 1;


select sys_period, * from o_address;
select sys_period, * from o_address_history;

delete from address where id = 5;



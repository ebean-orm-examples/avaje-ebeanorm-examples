ALTER TABLE o_address ADD COLUMN sys_period tstzrange NOT NULL;

CREATE TABLE o_address_history (LIKE o_address);

CREATE VIEW o_address_with_history AS SELECT * FROM o_address UNION ALL SELECT * FROM o_address_history;




CREATE OR REPLACE FUNCTION o_address_history_version() RETURNS TRIGGER AS $$
BEGIN

  IF (TG_OP = 'INSERT') THEN
    NEW.sys_period = tstzrange(CURRENT_TIMESTAMP,null);
    RETURN NEW;

  ELSIF (TG_OP = 'UPDATE') THEN
    insert into o_address_history (sys_period, id, line1, line2, city, country_code, version, when_created, when_updated)
    VALUES (tstzrange(lower(OLD.sys_period), CURRENT_TIMESTAMP), OLD.id, OLD.line1, OLD.line2, OLD.city, OLD.country_code, OLD.version, OLD.when_created, OLD.when_updated);
    NEW.sys_period = tstzrange(CURRENT_TIMESTAMP,null);
    RETURN NEW;

  ELSEIF (TG_OP = 'DELETE') THEN
    insert into o_address_history (sys_period, id, line1, line2, city, country_code, version, when_created, when_updated)
    VALUES (tstzrange(lower(OLD.sys_period), CURRENT_TIMESTAMP), OLD.id, OLD.line1, OLD.line2, OLD.city, OLD.country_code, OLD.version, OLD.when_created, OLD.when_updated);
    RETURN OLD;

  END IF;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER o_address_versioning_upd
BEFORE INSERT OR UPDATE OR DELETE ON o_address
FOR EACH ROW EXECUTE PROCEDURE o_address_history_version();

--
-- END --------------------------
--

insert into country (code, name) VALUES ('NZ', 'New Zealand');
insert into country (code, name) VALUES ('US', 'United States');
insert into country (code, name) VALUES ('AU', 'Australia');

select * from country;

insert into o_address (line1, city, country_code, version, when_created, when_updated)
values ('9 Queen St','Auckland','NZ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into o_address (line1, city, country_code, version, when_created, when_updated)
values ('23 Bee St','Wellington','NZ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


update o_address set line1 = '42 Good luck' where id = 1;
update o_address set line2 = 'central' where id = 1;


select sys_period, * from o_address;
select sys_period, * from o_address_history;

delete from address where id = 5;



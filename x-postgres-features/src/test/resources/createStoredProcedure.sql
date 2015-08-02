create or replace function sp_insert (p_name VARCHAR, OUT p_id INTEGER)
as $$

DECLARE

BEGIN
  insert into p_customer (name, inactive, version, when_created, when_updated) values (p_name, true, 1, current_timestamp, current_timestamp);
  SELECT currval(pg_get_serial_sequence('p_customer','id')) into p_id;
END;
$$
LANGUAGE 'plpgsql'

--  select sp_insert('asd');

select * from p_customer ORDER BY id desc;



CREATE OR REPLACE FUNCTION sp_insert2 (INOUT p_name VARCHAR, OUT p_id INTEGER)
AS $$
BEGIN
  insert into p_customer (name, inactive, version, when_created, when_updated) values (p_name, true, 1, current_timestamp, current_timestamp);
  SELECT currval(pg_get_serial_sequence('p_customer','id')) into p_id;
END;
$$
LANGUAGE 'plpgsql'
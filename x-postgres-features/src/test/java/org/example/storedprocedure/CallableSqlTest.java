package org.example.storedprocedure;

import com.avaje.ebean.CallableSql;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import org.example.ExampleBaseTestCase;
import org.junit.Test;

import java.sql.Types;

import static org.assertj.core.api.Assertions.assertThat;


public class CallableSqlTest extends ExampleBaseTestCase {

  @Test
  public void testCallable() {

    EbeanServer server = Ebean.getDefaultServer();

    //create or replace function sp_insert (p_name VARCHAR, OUT p_id INTEGER)
    CallableSql callableSql = server.createCallableSql("{call sp_insert(?, ?)}");
    callableSql.setParameter(1, "rob name");
    callableSql.registerOut(2, Types.INTEGER);

    server.execute(callableSql);

    Integer value = (Integer)callableSql.getObject(2);

    assertThat(value).isNotNull();
  }


  @Test
  public void testCallable_withInOut() {

    EbeanServer server = Ebean.getDefaultServer();

    //create or replace function sp_insert (p_name VARCHAR, OUT p_id INTEGER)
    CallableSql callableSql = server.createCallableSql("{call sp_insert2(?, ?)}");
    callableSql.setParameter(1, "rob name");
    callableSql.registerOut(1, Types.VARCHAR);
    callableSql.registerOut(2, Types.INTEGER);

    server.execute(callableSql);

    String nameOut = (String)callableSql.getObject(1);
    Integer intOut = (Integer)callableSql.getObject(2);

    assertThat(nameOut).isNotNull();
    assertThat(intOut).isNotNull();
  }
}

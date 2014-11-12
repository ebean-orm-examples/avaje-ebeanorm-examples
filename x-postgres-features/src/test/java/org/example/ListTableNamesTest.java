package org.example;

import java.util.List;

import org.junit.Test;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;

/**
 * Test the use of HStore being mapped to a Map<String,String> value.
 */
public class ListTableNamesTest extends ExampleBaseTestCase {

  @Test
  public void test() {

    String sql = 
          " SELECT table_name "
        + " FROM information_schema.tables "
        + " WHERE table_schema = 'public'"
        + " AND table_name like :name";

    SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
    sqlQuery.setParameter("name", "o_%");
    
    List<SqlRow> rows = sqlQuery.findList();
    for (SqlRow sqlRow : rows) {
      String tableName = sqlRow.getString("table_name");
      System.out.println(tableName);
    }
    
  }

}

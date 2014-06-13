package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;

public class RawJdbcHstoreTest extends ExampleBaseTestCase {

  @Test
  public void test() throws SQLException {
    
    Transaction txn = Ebean.beginTransaction();
    try {
      Connection connection = txn.getConnection();
      
      String insert = "update p_customer set tags = ? where id = 1";
      PreparedStatement pstmt1 = connection.prepareStatement(insert);
      
      Map<String,String> map = new HashMap<>();
      map.put("one","123123");
      map.put("two","hello");
      
      pstmt1.setObject(1, map);
      int rows = pstmt1.executeUpdate();
      System.out.println("updated rows "+rows);
      
      String sql = "select id, name, tags from p_customer";
      PreparedStatement pstmt = connection.prepareStatement(sql);
      
      ResultSet rset = pstmt.executeQuery();
      
      while( rset.next()) {
        Long id = rset.getLong(1);
        String name = rset.getString(2);
        Object tags = rset.getObject(3);

        System.out.println("rows:"+id+" "+name+" "+tags);
      } 
      
      
    } finally {
      txn.end();
    }
    
  }
}

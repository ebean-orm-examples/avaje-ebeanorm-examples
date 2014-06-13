package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;

public class RawJdbcInsertWithGeneratedKeyTest extends ExampleBaseTestCase {

  @Test
  public void test() throws SQLException {
    
    Transaction txn = Ebean.beginTransaction();
    try {
      Connection connection = txn.getConnection();
      
      String sql = "insert into p_customer (name, version, when_created, when_updated) values (?, 1, now(), now())";
      PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"id"});
      pstmt.setString(1, "Hello Rob");
      int rows = pstmt.executeUpdate();
      
      Long idValue = null;
      ResultSet resultSet = pstmt.getGeneratedKeys();
      if(resultSet.next()) {
        idValue = resultSet.getLong(1);
      }
      
      System.out.println("rows:"+rows+"  idValue:"+idValue);
      
    } finally {
      txn.end();
    }
    
  }
}

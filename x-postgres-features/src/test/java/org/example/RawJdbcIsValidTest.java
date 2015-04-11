package org.example;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RawJdbcIsValidTest extends ExampleBaseTestCase {

  @Test
  public void test() throws SQLException {
    
    Transaction txn = Ebean.beginTransaction();
    try {
      Connection connection = txn.getConnection();

      boolean valid = connection.isValid(1000);
      assertTrue(valid);

    } finally {
      txn.end();
    }
    
  }
}

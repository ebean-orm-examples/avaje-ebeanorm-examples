package org.example;

import org.example.domain.Customer;
import org.junit.Test;

import com.avaje.ebean.Transaction;

public class BatchInsertTest extends ExampleBaseTestCase {

  @Test
  public void testInsert() {
    
    Transaction txn = Customer.db().beginTransaction();
    try {
      txn.setBatchMode(true);
      txn.setBatchSize(10);
      
      // turn of getGeneratedKeys if we don't need to use the 
      // customer bean instances after we have inserted them
      txn.setBatchGetGeneratedKeys(false);
      
      for (int i = 0; i < 40; i++) {
        Customer c = new Customer();
        c.setName("batch insert test "+i);
        c.save();
      }
      
      txn.commit();
      
    } finally {
      txn.end();
    }
    
  }
}

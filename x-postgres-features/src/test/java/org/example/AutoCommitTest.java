package org.example;

import org.example.domain.Customer;
import org.junit.Test;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;

public class AutoCommitTest extends ExampleBaseTestCase {

  @Test
  public void test() {

    Ebean.getServer(null);
    
    Customer customer = new Customer();
    customer.setName("Rob");
    customer.save();
    
    Transaction txn = Customer.db().beginTransaction();
    txn.setBatchMode(true);
    try {
    
      for (int i = 0; i < 2; i++) {
        Customer cust = new Customer();
        cust.setName("Batch insert "+i);
        cust.save();
      }
      
      txn.commit();
      
    } finally {
      txn.end();
    }
  }
  
}

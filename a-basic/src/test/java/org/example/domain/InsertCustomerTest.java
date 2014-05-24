package org.example.domain;

import static org.junit.Assert.assertEquals;

import org.example.ExampleBaseTestCase;
import org.junit.Test;

public class InsertCustomerTest extends ExampleBaseTestCase {

  
  @Test
  public void test() {
    
    Customer customer = new Customer();
    customer.setName("Rob");
    
    customer.save();
    
    
    Customer fetched = Customer.find.byId(customer.getId());
    
    assertEquals("Rob", fetched.getName());
    
  }
  
  
}

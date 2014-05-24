package org.example.domain;

import static org.junit.Assert.*;

import java.util.List;

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
  
  
  @Test
  public void testQuery() {
    
    List<Customer> customers = 
        Customer.find.
          where().ilike("name", "rob%")
          .findList();
    
    assertTrue(customers.isEmpty());
    
  }
  
}

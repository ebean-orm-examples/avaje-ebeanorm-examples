package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.domain.Customer;
import org.junit.Test;

public class HstoreTest extends ExampleBaseTestCase {

  @Test
  public void testInsert() {
    
    Customer customer = new Customer();
    customer.setName("John");
    
    Map<String,String> tags = new HashMap<>();
    tags.put("height","100");
    tags.put("length","400");
    tags.put("trim","large");
    
    customer.setTags(tags);
    customer.save();
    
    
    Customer customer2 = new Customer();
    customer2.setName("Ringo");
    
    Map<String,String> tags2 = new HashMap<>();
    tags2.put("height","150");
    tags2.put("length","400");
    
    customer2.setTags(tags2);
    customer2.save();
    
    List<Customer> all = Customer.find.all();
    for (Customer cust : all) {
      System.out.println(cust);
    }
    
    List<Customer> some = Customer.find.where()
        .raw("defined(tags,'trim')")
        .findList();
    
    for (Customer largeHeight : some) {
      System.out.println(largeHeight);
    }
    
  }
}

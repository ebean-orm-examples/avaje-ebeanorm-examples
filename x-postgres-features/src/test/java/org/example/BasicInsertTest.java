package org.example;

import java.util.HashMap;
import java.util.Map;

import org.example.domain.Customer;
import org.junit.Assert;
import org.junit.Test;

public class BasicInsertTest extends ExampleBaseTestCase {

  @Test
  public void testInsert() {
    
    Customer customer = new Customer();
    customer.setName("Frankie");
    
    Map<String,String> tags = new HashMap<>();
    tags.put("height","100");
    tags.put("length","400");
    tags.put("trim","large");
    
    customer.setTags(tags);
    customer.save();
    
    Assert.assertNotNull(customer.getId());
    
  }
}

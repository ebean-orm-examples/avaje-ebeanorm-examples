package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.example.domain.Customer;
import org.junit.Test;

import com.avaje.ebean.text.json.JsonContext;

public class HstoreJsonTest extends ExampleBaseTestCase {

  @Test
  public void test() throws IOException {
    
    
    Customer customer = new Customer();
    customer.setName("SomeCustomer");
    
    Map<String,String> tags = new HashMap<>();
    tags.put("height","100");
    tags.put("length","200");
    
    customer.setTags(tags);
    
    JsonContext jsonContext = Customer.db().json();
    String jsonString = jsonContext.toJson(customer);
    System.out.println(jsonString);


  }
}

package org.example;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.Transactional;
import org.example.domain.Customer;
import org.example.domain.User;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BasicInsertTest extends ExampleBaseTestCase {

  @Transactional
  @Test
  public void testInsert() throws IOException {
    
    Customer customer = new Customer();
    customer.setName("Frankie");
    
    Map<String,String> tags = new HashMap<>();
    tags.put("height","100");
    tags.put("length","400");
    tags.put("trim","large");
    tags.put("colour","red");

    customer.setTags(tags);
    customer.save();
    
    Assert.assertNotNull(customer.getId());

    String json = Ebean.json().toJson(customer);
    System.out.println(json);

    Customer customer1 = Ebean.json().toBean(Customer.class, json);

    assertEquals(customer.getId(), customer1.getId());
    assertEquals(customer.getName(), customer1.getName());
    assertEquals(customer.getTags().size(), customer1.getTags().size());
    assertEquals(customer.getTags().get("height"), customer1.getTags().get("height"));
    assertEquals("400", customer1.getTags().get("length"));
    assertEquals("large", customer1.getTags().get("trim"));


    Customer customer2 = Customer.find.byId(customer.getId());

    assertEquals(customer.getId(), customer2.getId());
    assertEquals(customer.getName(), customer2.getName());
    assertEquals(customer.getTags().size(), customer2.getTags().size());
    assertEquals(customer.getTags().get("height"), customer2.getTags().get("height"));
    assertEquals("400", customer2.getTags().get("length"));
    assertEquals("large", customer2.getTags().get("trim"));

    customer.setTags(new HashMap<String, String>());
    String jsonWithEmpty = Ebean.json().toJson(customer);
    System.out.println("WITH EMPTY-> "+jsonWithEmpty);
    Customer customerWithEmpty = Ebean.json().toBean(Customer.class, jsonWithEmpty);
    assertEquals(0, customerWithEmpty.getTags().size());


    customer.setTags(null);
    String jsonWithNull = Ebean.json().toJson(customer);
    System.out.println("WITH NULL-> " + jsonWithNull);
    Customer customerWithNull = Ebean.json().toBean(Customer.class, jsonWithNull);
    assertNull(customerWithNull.getTags());
  }

  @Test
  public void testInsertUser() {
    
    User user= new User();
    user.setName("Frankie");

    Ebean.save(user);
    
  }
}

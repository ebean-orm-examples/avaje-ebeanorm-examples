package org.example;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.domain.Customer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test the use of HStore being mapped to a Map<String,String> value.
 *
 * You need to first install the hstore extension:
 *
 * psql mydb -c 'create extension hstore;'
 */
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
    
    // find John but not Ringo ...
    List<Customer> some = Customer.find.where()
        .raw("defined(tags,'trim')")
        .findList();
    
    for (Customer largeHeight : some) {
      System.out.println(largeHeight);
    }
    
  }
  
  @Test
  public void testUpdateWhenNoChange() {
    
    Customer customer = new Customer();
    customer.setName("Frank");
    
    Map<String,String> tags = new HashMap<>();
    tags.put("height","100");
    tags.put("length","400");
    
    customer.setTags(tags);
    customer.save();
    
    Timestamp whenUpdated = customer.getWhenUpdated();
    
    Customer cust2 = Customer.find.byId(customer.getId());
    
    // no change so no update occurs
    cust2.save();
    
    Assert.assertEquals(whenUpdated, cust2.getWhenUpdated());
    
    // set to null so update will occur
    cust2.setTags(null);
    cust2.save();
    
    Assert.assertNotEquals(whenUpdated, cust2.getWhenUpdated());
    
    // check that the value is now null
    Customer cust3 = Customer.find.byId(customer.getId());
    Assert.assertNull(cust3.getTags());
    
  }
  
  

  @Test
  public void testModifyWithPutAndClear() {
    
    Customer customer = new Customer();
    customer.setName("diff on map");
    
    Map<String,String> tags = new HashMap<>();
    tags.put("height","100");
    
    customer.setTags(tags);
    customer.save();

    Customer custToMod = Customer.find.byId(customer.getId());
    Assert.assertEquals(Long.valueOf(1), Long.valueOf(custToMod.getVersion()));
    
    Map<String, String> tags2 = custToMod.getTags();
    tags2.put("length", "200");
    tags2.put("height", "23");
    
    System.out.println("-- update after put new values...");
    custToMod.update();
    Assert.assertEquals(Long.valueOf(2), Long.valueOf(custToMod.getVersion()));
    
    custToMod.getTags().clear();
    System.out.println("-- update after a clear ...");
    custToMod.save();

    Assert.assertEquals(Long.valueOf(3), Long.valueOf(custToMod.getVersion()));

  }
  
}

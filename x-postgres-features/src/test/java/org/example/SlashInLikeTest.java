package org.example;

import java.util.List;

import org.example.domain.Customer;
import org.junit.Assert;
import org.junit.Test;

public class SlashInLikeTest extends ExampleBaseTestCase {

  @Test
  public void test() {
    
    Customer customer = new Customer();
    customer.setName("slash\\monkey");
    customer.save();

    List<Customer> list = Customer.find.where().eq("name", "slash\\monkey").findList();    
    Assert.assertEquals("equals with slash", 1, list.size());

    list = Customer.find.where().like("name", "slash\\mon%").findList();
    Assert.assertEquals("like with slash", 1, list.size());

    list = Customer.find.where().raw("name like 'slash\\mon%' escape''").findList();
    Assert.assertEquals("like with slash using raw", 1, list.size());

    list = Customer.find.where().raw("name like ? escape''", "slash\\mon%").findList();
    Assert.assertEquals("like with slash using raw", 1, list.size());

    list = Customer.find.where().raw("name like 'slash\\mon%'").findList();
    Assert.assertEquals("raw like with no escape - not found", 0, list.size());

  }
  
}

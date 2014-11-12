package org.example.domain;


import org.example.ExampleBaseTestCase;
import org.example.service.LoadExampleData;
import org.junit.Test;

public class ExPartialObjects extends ExampleBaseTestCase {

  @Test
  public void test() {

    LoadExampleData.load();

    Customer customer = Customer.find
        .select("name")
        .where().idEq(1L)
        .findUnique();

    customer.hashCode();

  }


  @Test
  public void testFindPartial() {

    LoadExampleData.load();

    Order order = Order.find
        .select("status, orderDate, shipDate")
        .fetch("customer", "name")
        .fetch("details")
        .fetch("details.product", "sku")
        .where().idEq(1L)
        .findUnique();

    order.hashCode();

  }
}


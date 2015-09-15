package org.example.domain;


import org.example.ExampleBaseTestCase;
import org.example.service.LoadExampleData;
import org.junit.Test;

public class ExPartialObjects extends ExampleBaseTestCase {

  @Test
  public void test() {

    LoadExampleData.load();

    Customer customer = Customer.
        find.where().id.equalTo(1L)
        .select("name")
        .findUnique();

    customer.hashCode();

  }


  @Test
  public void testFindPartial() {

    LoadExampleData.load();

    Order order = Order.find.query()
        .select("status, orderDate, shipDate")
        .fetch("customer", "name")
        .fetch("details")
        .fetch("details.product", "sku")
        .where().idEq(1L)
        .findUnique();

    order.hashCode();

  }
}


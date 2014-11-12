package org.example.domain;


import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.QueryIterator;
import org.example.ExampleBaseTestCase;
import org.example.service.LoadExampleData;
import org.junit.Test;

import java.util.List;

public class ExFindIterate extends ExampleBaseTestCase {

  @Test
  public void simple() {

    LoadExampleData.load();

    QueryIterator<Order> orders = Order.find
        .select("status")
        .fetch("customer")
        .fetch("customer.contacts", new FetchConfig().query(1))
        .fetch("details", new FetchConfig().query(1))
        .orderBy("customer.name")
        .findIterate();

    try {
      while(orders.hasNext()) {
        Order order = orders.next();
        Customer customer = order.getCustomer();
        List<Contact> contacts = customer.getContacts();
        System.out.println(contacts);

      }
    } finally {
      orders.close();
    }


  }

}


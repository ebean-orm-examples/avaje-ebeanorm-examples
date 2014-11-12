package org.example.domain;


import com.avaje.ebean.FetchConfig;
import org.example.ExampleBaseTestCase;
import org.example.service.LoadExampleData;
import org.junit.Test;

import java.util.List;

public class ExQueryJoins extends ExampleBaseTestCase {

  @Test
  public void simple() {

    LoadExampleData.load();

    List<Order> orders = Order.find
        .select("status")
        .fetch("customer")
        .fetch("customer.contacts")
        .fetch("details")
        .orderBy("customer.name")
        .findList();

    orders.hashCode();

  }

  @Test
  public void deepOneToMany() {

    LoadExampleData.load();

    List<Customer> customers = Customer.find
        .fetch("contacts")
        .fetch("contacts.notes")
        .orderBy("name")
        .findList();

    customers.hashCode();

  }
}


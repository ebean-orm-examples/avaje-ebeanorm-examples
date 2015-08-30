package org.example.domain;


import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.QueryEachConsumer;
import org.example.ExampleBaseTestCase;
import org.example.domain.query.QOrder;
import org.example.domain.query.QOrderDetail;
import org.example.service.LoadExampleData;
import org.junit.Test;

import java.util.List;

public class ExFindIterate extends ExampleBaseTestCase {

  @Test
  public void simple() {

    LoadExampleData.load();

    ExpressionList<OrderDetail> detailsFilter = new QOrderDetail()
        .unitPrice.isNotNull()
        .getExpressionList();

    Customer cust1 = Customer.find.ref(1L);

    new QOrder()
        //.id.gt(0)
        .status.in(Order.Status.APPROVED, Order.Status.COMPLETE)
        .customer.equalTo(cust1)
        .details.filterMany(detailsFilter)
        .findList();

    new QOrder()
        .id.greaterThan(1)
        .findEach(new QueryEachConsumer<Order>() {
          @Override
          public void accept(Order order) {
            Customer customer = order.getCustomer();
            List<Contact> contacts = customer.getContacts();
            System.out.println(contacts);
          }
        });

//
//    QueryIterator<Order> orders = Order.find
//        .select("status")
//        .fetch("customer")
//        .fetch("customer.contacts", new FetchConfig().query(1))
//        .fetch("details", new FetchConfig().query(1))
//        .orderBy("customer.name")
//        .findIterate();
//
//    try {
//      while(orders.hasNext()) {
//        Order order = orders.next();
//        Customer customer = order.getCustomer();
//        List<Contact> contacts = customer.getContacts();
//        System.out.println(contacts);
//
//      }
//    } finally {
//      orders.close();
//    }


  }

}


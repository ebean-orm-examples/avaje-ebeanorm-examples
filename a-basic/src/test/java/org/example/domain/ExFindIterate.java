package org.example.domain;


import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.QueryEachConsumer;
import org.example.ExampleBaseTestCase;
import org.example.domain.query.QContact;
import org.example.domain.query.QCustomer;
import org.example.domain.query.QOrder;
import org.example.domain.query.QOrderDetail;
import org.example.service.LoadExampleData;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class ExFindIterate extends ExampleBaseTestCase {

  @Test
  public void simple() {

    LoadExampleData.load();

    Date oneWeekAgo = new Date(System.currentTimeMillis());

    ExpressionList<Order> recentNewOrders = new QOrder()
        .orderDate.after(oneWeekAgo)
        .status.eq(Order.Status.NEW)
        .getExpressionList();

    QCustomer cus = QCustomer.alias();
    QContact con = QContact.alias();

    List<Customer> customers = new QCustomer()
        // query tuning
        .select(cus.name, cus.inactive)
        .contacts.fetch(con.email, con.firstName)
        .contacts.notes.fetchAll()

        // predicates
        .name.ilike("Rob")
        .orders.filterMany(recentNewOrders)
        .findList();


//    ExpressionList<OrderDetail> detailsFilter = new QOrderDetail()
//        .unitPrice.isNotNull()
//        .getExpressionList();
//
//    Customer cust = Customer.find.ref(1L);
//
//    new QOrder()
//        .status.in(Order.Status.APPROVED, Order.Status.COMPLETE)
//        .customer.equalTo(cust)
//        .details.filterMany(detailsFilter)
//        .findList();

//    new QOrder()
//        .id.greaterThan(1)
//        .findEach(new QueryEachConsumer<Order>() {
//          @Override
//          public void accept(Order order) {
//            Customer customer = order.getCustomer();
//            List<Contact> contacts = customer.getContacts();
//            System.out.println(contacts);
//          }
//        });

  }

}


package org.example.domain.finder;

import com.avaje.ebean.PagedList;
import org.example.ExampleBaseTestCase;
import org.example.domain.Address;
import org.example.domain.Contact;
import org.example.domain.Customer;
import org.example.domain.Order;
import org.example.domain.OrderDetail;
import org.example.domain.Product;
import org.example.service.LoadExampleData;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.List;

public class OrderFinderTest extends ExampleBaseTestCase {

  @BeforeClass
  public static void before() {
    //LoadExampleData.load();
  }

  @Test
  public void test() {

    List<Order> orders = Order.find.byStatus(Order.Status.NEW, Order.Status.APPROVED, Order.Status.COMPLETE);

    for (Order order : orders) {
      order.getOrderDate();
      order.getShipDate();
      order.getStatus();
      Customer customer = order.getCustomer();
      customer.getName();
//      Address billingAddress = customer.getBillingAddress();
//      if (billingAddress != null) {
//        billingAddress.getLine1();
//        billingAddress.getLine2();
//        billingAddress.getCity();
//        billingAddress.getCountry().getCode();
//      }

      List<OrderDetail> details = order.getDetails();
      for (OrderDetail detail : details) {
        detail.getUnitPrice();
        detail.getShipQty();
        Product product = detail.getProduct();
        product.getId();
        product.getName();
      }
    }

  }

  @Test
  public void testOther() {


    List<Order> orders = Order.find.byStatus(Order.Status.NEW, Order.Status.APPROVED);

    for (Order order : orders) {
      order.getOrderDate();
      order.getShipDate();
      Customer customer = order.getCustomer();
      customer.getName();
      //customer.getComments();

      List<Contact> contacts = customer.getContacts();
      for (Contact contact : contacts) {
        contact.getFirstName();
        contact.getLastName();
        //contact.getEmail();
      }
    }
  }

  @Test
  public void testOrdersSince() {

    OffsetDateTime odt = OffsetDateTime.now().minusDays(20);
    Date daysAgo = new Date(odt.toInstant().toEpochMilli());

    PagedList<Order> orderPaged = Order.find.newOrdersSince(daysAgo, 0);
    orderPaged.loadRowCount();
    List<Order> list = orderPaged.getList();
    orderPaged.getTotalRowCount();

    for (Order order : list) {
      order.getCustomer().getName();
      List<OrderDetail> details = order.getDetails();
      for (OrderDetail detail : details) {
        detail.getOrderQty();
        detail.getShipQty();
        detail.getUnitPrice();
        detail.getProduct().getName();
      }
    }

  }

}

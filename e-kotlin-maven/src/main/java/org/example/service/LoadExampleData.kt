package org.example.service;

import java.util.ArrayList;

import org.example.domain.Address;
import org.example.domain.Contact;
import org.example.domain.Country;
import org.example.domain.Customer;
import org.example.domain.Order;
import org.example.domain.Order.Status;
import org.example.domain.OrderDetail;
import org.example.domain.Product;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.TxRunnable;

public class LoadExampleData {

  var runOnce: Boolean = false;

  val server: EbeanServer = Ebean.getServer(null);

  synchronized fun load() {

    if (runOnce) {
      return;
    }

    server.beginTransaction();
    try {
      deleteAll();
      insertCountries();
      insertProducts();
      insertTestCustAndOrders();

      server.commitTransaction();
    } finally {
      server.endTransaction();
    }
//    server.execute(TxRunnable() {
//      fun run() {
//        deleteAll();
//        insertCountries();
//        insertProducts();
//        insertTestCustAndOrders();
//      }
//    });
    runOnce = true;
  }

  fun deleteAll() {
//    Ebean.execute(TxRunnable() {
//      fun run() {

        // Ebean.currentTransaction().setBatchMode(false);

        // orm update use bean name and bean properties
        // server.createUpdate(OrderShipment.class, "delete from orderShipment").execute();

        server.createUpdate(javaClass<OrderDetail>(), "delete from orderDetail").execute();
        server.createUpdate((javaClass<Order>()), "delete from order").execute();
        server.createUpdate((javaClass<Contact>()), "delete from contact").execute();
        server.createUpdate((javaClass<Customer>()), "delete from Customer").execute();
        server.createUpdate((javaClass<Address>()), "delete from address").execute();

        // sql update uses table and column names
        server.createSqlUpdate("delete from o_country").execute();
        server.createSqlUpdate("delete from o_product").execute();

//      }
//    });
  }

  fun insertCountries() {

//    server.execute(TxRunnable() {
//      fun run() {
        val nz = Country();
        nz.code = "NZ";
        nz.name = "New Zealand";
        server.save(nz);

        val au = Country()
        au.code = "AU"
        au.name = "Australia"
        server.save(au);
//      }
//    });
  }

  fun insertProducts() {

//    server.execute(TxRunnable() {
//      fun run() {
        var product = Product();
        product.name = "Chair";
        product.sku = "C001";
        server.save(product);

        product = Product();
        product.name = "Desk";
        product.sku = "DSK1";
        server.save(product);

        product = Product();
        product.name = "Computer";
        product.sku = "C002";
        server.save(product);

        product = Product();
        product.name = "Printer";
        product.sku = "C003";
        server.save(product);
//      }
//    });
  }

  fun insertTestCustAndOrders() {

//    // How to do this better in Kotlin ??
//    Ebean.execute(TxRunnable() {
//      fun run() {
        val cust1 = insertCustomer("Rob");
        val cust2 = insertCustomerNoAddress();
        insertCustomerFiona();
        insertCustomerNoContacts("NocCust");

        createOrder1(cust1);
        createOrder2(cust2);
        createOrder3(cust1);
        createOrder4(cust1);
//      }
//    });
  }

  fun createCustAndOrder(custName: String): Customer {

    val customer = insertCustomer(custName);
    createOrder1(customer);
    return customer;
  }

  fun createOrderCustAndOrder(custName: String): Order {

    val customer = insertCustomer(custName);
    return createOrder1(customer);
  }

  var contactEmailNum: Int = 1;

  fun insertCustomerFiona(): Customer {

    val c = createCustomer("Fiona", "12 Apple St", "West Coast Rd", 1);

    c.addContact(createContact("Fiona", "Black"));
    c.addContact(createContact("Tracy", "Red"));

    Ebean.save(c);
    return c;
  }

  fun createContact(firstName: String, lastName: String): Contact {

    val contact = Contact();
    contact.firstName = firstName;
    contact.lastName = lastName;
    contact.email = (contact.lastName + (contactEmailNum++) + "@test.com").toLowerCase();
    return contact;
  }

  fun insertCustomerNoContacts(name: String): Customer {

    val c = createCustomer("Roger", "15 Kumera Way", "Bos town", 1);
    c.name = name;

    c.save()
    return c;
  }

  fun insertCustomerNoAddress(): Customer {

    val c = Customer();
    c.name = "Cust NoAddress";
    c.addContact(createContact("Jack", "Black"));

    c.save()
    return c;
  }

  fun insertCustomer(name: String): Customer {
    val c = createCustomer(name, "1 Banana St", "P.O.Box 1234", 1);
    c.save()
    return c;
  }

  fun createCustomer(name: String, shippingStreet: String?, billingStreet: String?, contactSuffix: Int): Customer {

    val customer = Customer();
    customer.name = name;
    if (contactSuffix > 0) {
      customer.addContact(Contact.of("Jim" + contactSuffix, "Cricket"));
      customer.addContact(Contact.of("Fred" + contactSuffix, "Blue"));
      customer.addContact(Contact.of("Bugs" + contactSuffix, "Bunny"));
    }

    if (shippingStreet != null) {
      val shipAddress = Address();
      shipAddress.line1 = shippingStreet;
      shipAddress.line2 = "Sandringham";
      shipAddress.city = "Auckland";
      shipAddress.country = Country.ref("NZ")

      customer.shippingAddress = shipAddress;
    }

    if (billingStreet != null) {
      val billAddress = Address();
      billAddress.line1 = billingStreet;
      billAddress.line2 = "St Lukes";
      billAddress.city = "Auckland";
      billAddress.country = Country.ref("NZ")

      customer.billingAddress = billAddress;
    }

    return customer;
  }

  fun createOrder1(customer: Customer): Order {

    val product1 = Product.ref(1)
    val product2 = Product.ref(2)
    val product3 = Product.ref(3)

    val order = Order();
    order.customer = customer;
    order.add(product1, 5, 10.50)
    order.add(product2, 3, 1.10)
    order.add(product3, 1, 2.00)

    order.save()

    return order;
  }

  fun createOrder2(customer: Customer) {

    val product1 = Product.ref(1)

    val order = Order();
    order.status = Status.SHIPPED;
    order.customer = customer;
    order.add(product1, 4, 10.50)
    order.save();
  }

  fun createOrder3(customer: Customer) {

    val product1 = Product.ref(1)
    val product3 = Product.ref(3)

    val order = Order()
    order.status = Status.COMPLETE
    order.customer = customer;
    order.add(product1, 3, 10.50)
    order.add(product3, 40, 2.10)

    order.save()
  }

  fun createOrder4(customer: Customer) {

    val order = Order()
    order.customer = customer
    order.save()
  }
}

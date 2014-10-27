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

    server.execute({
      deleteAll();
      insertCountries();
      insertProducts();
      insertTestCustAndOrders();
    });
    runOnce = true;
  }

  fun deleteAll() {

    // using an 'extension function'
    txn({

      // orm update use bean name and bean properties
      server.createUpdate(javaClass<OrderDetail>(), "delete from orderDetail").execute();
      server.createUpdate((javaClass<Order>()), "delete from order").execute();
      server.createUpdate((javaClass<Contact>()), "delete from contact").execute();
      server.createUpdate((javaClass<Customer>()), "delete from Customer").execute();
      server.createUpdate((javaClass<Address>()), "delete from address").execute();

      // sql update uses table and column names
      server.createSqlUpdate("delete from o_country").execute();
      server.createSqlUpdate("delete from o_product").execute();
    })
  }

  fun insertCountries() {

    Country("NZ", "New Zealand 22").save()
    Country("AU", "Australia 22").save()
  }

  fun insertProducts() {

    server.execute({

      // use alternate constructor function ...
      Product("Chair", "C001").save()
      Product("Desk", "DSK1").save()
      Product("C002", "Computer").save()

      // use set properties style ...
      val product = Product()
      product.name = "Printer"
      product.sku = "C003"
      product.save()
    });
  }

  fun insertTestCustAndOrders() {

    // TxRunnable is not really required ...
    server.execute(TxRunnable() {
      val cust1 = insertCustomer("Rob");
      val cust2 = insertCustomerNoAddress();
      insertCustomerFiona();
      insertCustomerNoContacts("NocCust");

      createOrder1(cust1);
      createOrder2(cust2);
      createOrder3(cust1);
      createOrder4(cust1);
    });
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

    val c = createCustomer("Fiona", "12 Apple St", "West Coast Rd", 1)
    c.addContact(createContact("Fiona", "Black"))
    c.addContact(createContact("Tracy", "Red"))

    c.save()
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

    val customer = createCustomer("Roger", "15 Kumera Way", "Bos town", 1);
    customer.name = name;

    customer.save()
    return customer;
  }

  fun insertCustomerNoAddress(): Customer {

    val customer = Customer();
    customer.name = "Cust NoAddress";
    customer.addContact(createContact("Jack", "Black"));

    customer.save()
    return customer;
  }

  fun insertCustomer(name: String): Customer {
    val customer = createCustomer(name, "1 Banana St", "P.O.Box 1234", 1);
    customer.save()
    return customer;
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

    return with(Order()) {
      this.customer = customer;
      add(product1, 5, 10.50)
      add(product2, 3, 1.10)
      add(product3, 1, 2.00)
      save()
      this
    }
  }

  fun createOrder2(customer: Customer) {

    val product1 = Product.ref(1)

    with(Order()) {
      status = Status.SHIPPED;
      this.customer = customer;
      add(product1, 4, 10.50)
      save();
    }
  }

  fun createOrder3(customer: Customer) {

    val product1 = Product.ref(1)
    val product3 = Product.ref(3)

    with(Order()) {
      status = Status.COMPLETE
      this.customer = customer;
      add(product1, 3, 10.50)
      add(product3, 40, 2.10)
      save()
    }
  }

  fun createOrder4(customer: Customer) {

    with(Order()) {
      this.customer = customer;
      save();
    }
  }
}

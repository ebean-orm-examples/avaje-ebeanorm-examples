package org.example.service

import com.avaje.ebean.Ebean
import com.avaje.ebean.EbeanServer
import groovy.transform.CompileStatic
import org.example.domain.*
import org.example.domain.Order.Status

@CompileStatic
public class LoadExampleData {

	private static boolean runOnce

	private static EbeanServer server = Ebean.getServer(null)

	public static synchronized void load() {
		if (runOnce) {
			return
		}

		final LoadExampleData me = new LoadExampleData()

		server.run {
			me.deleteAll()
			me.insertCountries()
			me.insertProducts()
			me.insertTestCustAndOrders()
		}

		runOnce = true
	}

	public void deleteAll() {
		Ebean.run {
			// Ebean.currentTransaction().setBatchMode(false)

			// orm update use bean name and bean properties
			// server.createUpdate(OrderShipment, "delete from orderShipment").execute()

			server.createUpdate(OrderDetail, "delete from orderDetail").execute()
			server.createUpdate(Order, "delete from order").execute()
			server.createUpdate(Contact, "delete from contact").execute()
			server.createUpdate(Customer, "delete from Customer").execute()
			server.createUpdate(Address, "delete from address").execute()

			// sql update uses table and column names
			server.createSqlUpdate("delete from o_country").execute()
			server.createSqlUpdate("delete from o_product").execute()
		}
	}

	public void insertCountries() {
		server.run {
			server.save(new Country(code: 'NZ', name: 'New Zealand'))
			server.save(new Country(code: 'AU', name: 'Australia'))
			server.save(new Country(code: 'US', name: 'United States of America'))
			server.save(new Country(code: 'DE', name: 'Germany'))
			server.save(new Country(code: 'UK', name: 'United Kingdom'))
		}
	}

	public void insertProducts() {
		server.run {
			server.save(new Product(name: 'Chair', sku: 'C001'))
			server.save(new Product(name: 'Desk', sku: 'Printer'))
			server.save(new Product(name: 'Computer', sku: 'C002'))
			server.save(new Product(name: 'Printer', sku: 'C003'))
		}
	}

	public void insertTestCustAndOrders() {
		Ebean.run {
			Customer cust1 = insertCustomer("Rob")
			Customer cust2 = insertCustomerNoAddress()
			insertCustomerFiona()
			insertCustomerNoContacts("NocCust")

			createOrder1(cust1)
			createOrder2(cust2)
			createOrder3(cust1)
			createOrder4(cust1)
		}
	}

	public static Customer createCustAndOrder(String custName) {
		LoadExampleData me = new LoadExampleData()
		Customer cust1 = insertCustomer(custName)
		me.createOrder1(cust1)
		return cust1
	}

	public static Order createOrderCustAndOrder(String custName) {

		LoadExampleData me = new LoadExampleData()
		Customer cust1 = insertCustomer(custName)
		Order o = me.createOrder1(cust1)
		return o
	}

	private static int contactEmailNum = 1

	private Customer insertCustomerFiona() {

		Customer c = createCustomer("Fiona", "12 Apple St", "West Coast Rd", 1)

		c.addContact(createContact("Fiona", "Black"))
		c.addContact(createContact("Tracy", "Red"))

		Ebean.save(c)
		return c
	}

	public static Contact createContact(String firstName, String lastName) {
		Contact contact = new Contact(firstName: firstName, lastName: lastName)

		contact.email = "${contact.lastName}${contactEmailNum++}@test.com".toLowerCase()

		return contact
	}

	private Customer insertCustomerNoContacts(String name) {

		Customer c = createCustomer("Roger", "15 Kumera Way", "Bos town", 1)
		c.name = name

		Ebean.save(c)

		return c
	}

	private Customer insertCustomerNoAddress() {

		Customer c = new Customer()
		c.name = "Cust NoAddress"
		c.addContact(createContact("Jack", "Black"))

		Ebean.save(c)
		return c
	}

	private static Customer insertCustomer(String name) {
		Customer c = createCustomer(name, "1 Banana St", "P.O.Box 1234", 1)
		Ebean.save(c)
		return c
	}

	public static Customer createCustomer(String name, String shippingStreet, String billingStreet, int contactSuffix) {

		Customer newCustomer = new Customer(name:name)

		if (contactSuffix > 0) {
			newCustomer.addContact(new Contact(firstName:"Jim" + contactSuffix, lastName:"Cricket"))
			newCustomer.addContact(new Contact(firstName:"Fred" + contactSuffix, lastName:"Blue"))
			newCustomer.addContact(new Contact(firstName:"Bugs" + contactSuffix, lastName:"Bunny"))
		}

		if (shippingStreet != null) {
			newCustomer.shippingAddress = new Address(line1: shippingStreet, line2:"Sandringham", city: "Auckland",
				country:Ebean.getReference(Country, "NZ"))
		}

		if (billingStreet != null) {
			newCustomer.billingAddress = new Address(line1: billingStreet, line2:"St Lukes", city: "Auckland",
				country:Ebean.getReference(Country, "NZ"))
		}

		return newCustomer
	}

	private Order createOrder1(Customer customer) {

		Product product1 = Ebean.getReference(Product, 1)
		Product product2 = Ebean.getReference(Product, 2)
		Product product3 = Ebean.getReference(Product, 3)

		Order order = new Order(customer:customer)

		order.details = [
			new OrderDetail(product:product1, orderQty: 5, unitPrice:10.50d),
			new OrderDetail(product:product2, orderQty: 3, unitPrice:1.10d),
			new OrderDetail(product:product3, orderQty: 1, unitPrice:2.00d)
		]

		//order.addShipment(new OrderShipment())

		Ebean.save(order)
		return order
	}

	private void createOrder2(Customer customer) {

		Product product1 = Ebean.getReference(Product, 1)

		Order order = new Order(status:Status.SHIPPED, customer: customer)

		order.details = [
			new OrderDetail(product: product1, orderQty: 4, unitPrice: 10.50d)
		]

		//order.addShipment(new OrderShipment())

		Ebean.save(order)
	}

	private void createOrder3(Customer customer) {

		Product product1 = Ebean.getReference(Product, 1)
		Product product3 = Ebean.getReference(Product, 3)

		Order order = new Order(status: Status.COMPLETE, customer: customer)

		order.details = [
			new OrderDetail(product:product1, orderQty: 3, unitPrice:10.50d),
			new OrderDetail(product:product3, orderQty: 40, unitPrice:2.10d)
		]
		//order.addShipment(new OrderShipment())

		Ebean.save(order)
	}

	private void createOrder4(Customer customer) {

		Order order = new Order(customer:customer)

		//order.addShipment(new OrderShipment())

		Ebean.save(order)
	}
}

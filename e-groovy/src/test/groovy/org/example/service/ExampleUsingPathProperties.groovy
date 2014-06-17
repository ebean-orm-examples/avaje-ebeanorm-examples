package org.example.service

import com.avaje.ebean.Ebean
import com.avaje.ebean.Query
import com.avaje.ebean.text.PathProperties
import com.avaje.ebean.text.json.JsonContext
import groovy.transform.CompileStatic
import org.example.domain.Contact
import org.example.domain.Customer
import org.example.domain.Order
import org.junit.Test

/**
 *
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 */
@CompileStatic
class ExampleUsingPathProperties extends LoadAgentAtRuntime {
	public static void main(String[] args) {

//    new ExampleUsingPathProperties().runExample()
		new ExampleUsingPathProperties().runExampleUsingOrders()
	}

	@Test
	public void runExample() {
		LoadExampleData.load()

		List<Customer> list2 = Customer.find
			.select("name")
			.fetch("contacts")
			.findList()


		PathProperties pathProperties = PathProperties.parse("(id,version,name,contacts(id,email,version))")


		Query<Customer> query = Customer.find.query()
		pathProperties.apply(query)

		List<Customer> list = query.findList()

		JsonContext jsonContext = Customer.find.db().createJsonContext()

		println jsonContext.toJsonString(list, true)


		for (Customer customer : list2) {
			customer.name  // ??

			customer.contacts?.each { Contact contact ->
				println "contact: ${contact.firstName} ${contact.lastName}"
			}
		}
	}

	@Test
	public void runExampleUsingOrders() {

		LoadExampleData.load()

		PathProperties pathProperties = PathProperties.parse("(id,status,orderDate,customer(id,name),details(*,product(sku)))")

		Query<Order> query = Ebean.createQuery(Order)
		pathProperties.apply(query)

		List<Order> orders = query.where().gt("id", 1).findList()

		println Ebean.createJsonContext().toJsonString(orders, true)
	}
}

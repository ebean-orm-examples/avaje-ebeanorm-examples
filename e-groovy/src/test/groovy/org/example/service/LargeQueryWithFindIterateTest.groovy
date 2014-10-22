package org.example.service

import com.avaje.ebean.QueryIterator
import com.avaje.ebean.QueryResultVisitor
import org.example.domain.Customer
import org.junit.Test

public class LargeQueryWithFindIterateTest extends ExampleBaseTestCase {

  @Test
  public void testFindIterate() {

    // insert 100 customers
    int j = 0
    for (int i = 0; i < 100; i++) {
      Customer customer = new Customer(name: "Hello"+j++)
      customer.save()      
    }

    // Use the eachEntity extension method
    Customer.find.query().select("id").where().eachEntity {
      println "got customter ${it.id} "
    }

    // Use the eachEntityBoolean extension method
    // ... allows you to stop the iteration by returning false
    Customer.find.query().select("id").where().eachEntityBoolean {
      println "got customter ${it.id} "
      return it.id < 10
    }

    // Similar to eachEntityBoolean using findVisit() ...
    Customer.find
      .query().select("id, name") //.fetch("contacts", new FetchConfig().lazy(20))
      .findVisit(new QueryResultVisitor<Customer>() {

        @Override
        boolean accept(Customer customer) {
          println "got name ${customer.id} ${customer.name}"
          // return false to stop the iteration early ...
          return customer.id < 10
        }
      })

    // 'Typical' use of QueryIterator which requires it to be closed
    // as per the finally block below
    QueryIterator<Customer> iterate =
      Customer.find
        .query().select("id, name") //.fetch("contacts", new FetchConfig().lazy(20))
        .findIterate()

    try {

      while (iterate.hasNext()) {
        Customer customer = iterate.next()
        // do something interesting with customer
        //customer.getContacts().size()
        println "got name ${customer.id} ${customer.name}"
      }

    } finally {
      // close the underlying resources held by the QueryIterator
      // those are:  ResultSet and Connection
      iterate.close()
    }



  }


}

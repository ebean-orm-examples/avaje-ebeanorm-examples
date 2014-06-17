package org.example.service

import com.avaje.ebean.QueryIterator
import org.example.domain.Customer
import org.junit.Test

public class LargeQueryWithFindIterateTest extends ExampleBaseTestCase {

  @Test
  public void testFindIterate() {

    // insert 1000 customers
    int j = 0
    for (int i = 0; i < 1000; i++) {
      Customer customer = new Customer(name: "Hello"+j++)
      customer.save()      
    }
    
    QueryIterator<Customer> iterate = 
        Customer.find
          .query().select("id") //.fetch("contacts", new FetchConfig().lazy(20))
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

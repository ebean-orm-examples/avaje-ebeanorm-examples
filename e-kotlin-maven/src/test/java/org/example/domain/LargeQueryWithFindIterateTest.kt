package org.example.domain;

import com.avaje.ebean.QueryResultVisitor;
import org.example.ExampleBaseTestCase;
import org.junit.Test;

import com.avaje.ebean.QueryIterator;
import org.avaje.agentloader.AgentLoader
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class LargeQueryWithFindIterateTest : ExampleBaseTestCase() {

  // How to best do a class static initialisation block? (to load the javaagent at runtime so I'd say its a rare requirement)
  class object {
    // load the enhancement agent 'early' prior to the bean classes like Customer being loaded
    val agentLoaded = AgentLoader.loadAgentFromClasspath("avaje-ebeanorm-agent", "debug=1;packages=org.example.**")
  }

  Test
  fun testFindIterate() {

    // insert some customers
    for (i in 0..30) {
      val customer = Customer();
      customer.name = "Hello"+i;
      customer.save();      
    }

    // Can I convert this QueryResultVisitor to a SAM function?
    // Using local FindVisit class for the moment
    Customer.find
      .select("id, name")
      .findVisit(FindVisit())

    // Perform the query using findIterate ... which means the
    // iterator MUST be closed (in the finally block below)
    val iterate =
        Customer.find
          .query().select("id, name")
          .findIterate();

    try {

      while (iterate.hasNext()) {
        val customer = iterate.next();
        // do something interesting with customer
        System.out.println("iterator got name ${customer.id} ${customer.name}");
      }

    } finally {
      // close the underlying resources held by the QueryIterator
      // those are:  ResultSet and Connection
      iterate.close();
    }

  }


  class FindVisit : QueryResultVisitor<Customer> {

    override fun accept(customer: Customer?): Boolean {
      System.out.println("vistor ... got name ${customer?.id} ${customer?.name}");
      return true;
    }
  }

}

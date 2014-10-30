package org.example.domain;

import com.avaje.ebean.QueryResultVisitor;
import org.example.ExampleBaseTestCase;
import org.junit.Test;

import org.avaje.agentloader.AgentLoader
import com.avaje.ebean.Ebean
import com.avaje.ebean.findEach
import com.avaje.ebean.findEachWhile

public class LargeQueryWithFindIterateTest : ExampleBaseTestCase() {

  // How to best do a class static initialisation block? (to load the javaagent at runtime so I'd say its a rare requirement)
  class object {
    // load the enhancement agent 'early' prior to the bean classes like Customer being loaded
    val agentLoaded = AgentLoader.loadAgentFromClasspath("avaje-ebeanorm-agent", "debug=1;packages=org.example.**")
  }

  Test
  fun testFindIterate() {

    // insert some customers
    Ebean.execute {
      for (i in 0..10) {
        val customer = Customer();
        customer.name = "Hello" + i;
        customer.save();
      }
    }


    // The following queries show how to process very large resultSets a bean at a time.
    // Internally Ebean uses a persistence context per per bean (and associated object graphs)
    // unlike findList() which holds the entire list of beans in memory before giving the list
    // to the caller for processing.

    Customer.find
        .select("id, name")
        .findVisit {
          System.out.println(" using findVisit ... ${it.id} ${it.name}")
          // stop iterating/processing when ... id > 2
          (it.id ?: 0) > 2;
        }

    Customer.find
        .select("id, name")
        .where()
        .findEach({
          val id = it?.id
          val name = it?.name
          System.out.println(" using findEach extension method - $id $name")
        })

    Customer.find
        .select("id, name")
        .where()
        .findEachWhile {
          System.out.println(" using findEachThat extension method ... ${it.id} ${it.name}")
          // stop iterating through the results if id > 5
          (it.id ?: 0) > 5;
        }


    // Perform the query using findIterate (Java7 style) ...
    // Note that the iterator MUST be closed (in the finally block below) or there
    // will be leaked jdbc resources

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

}

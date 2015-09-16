package org.example.domain;


import com.avaje.ebean.Ebean
import org.example.ExampleBaseTestCase;
import org.junit.Test;
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

public class InsertCustomerTest : ExampleBaseTestCase() {


  @Test
  fun test() {

    val customer = Customer("Roberto")
    //customer.name = "Roberto"
    customer.comments = "Say hello"

    // insert the customer
    customer.save();

    val fetched = Customer.byId(customer.id);

    // fetch using the Ebean singleton style
    val fetched2 = Ebean.find(Customer::class.java, customer.id);

    assertEquals("Roberto", fetched?.name);
    assertEquals("Roberto", fetched2?.name);
  }


  /**
   * Test showing an explicit transaction.
   */
  @Test
  fun testExplicitTransaction() {

    // create a transaction to wrap multiple saves
    val transaction = Ebean.beginTransaction();
    try {

      val customer = Customer();
      customer.name = "Roberto";
      customer.save();

      val otherCustomer = Customer();
      otherCustomer.name = "Franko";
      otherCustomer.save();

      transaction.commit();

    } finally {
      // this cleans up the transaction if something
      // fails in the try block
      transaction.end();
    }

  }


  /**
   * Test showing an explicit transaction with extra control
   * the use of jdbc batching.
   */
  @Test
  fun testExplicitTransactionWithBatchControls() {

    val transaction = Ebean.beginTransaction();
    try {

      // turn off cascade persist for this transaction
      transaction.setPersistCascade(false);

      // extra control over jdbc batching for this transaction
      transaction.setBatchGetGeneratedKeys(false);
      transaction.setBatchMode(true);
      transaction.setBatchSize(20);
      transaction.setBatchFlushOnQuery(false);

      val customer = Customer();
      customer.name = "Roberto";
      customer.save();

      val otherCustomer = Customer();
      otherCustomer.name = "Franko";
      otherCustomer.save();

      transaction.commit();

    } finally {
      transaction.end();
    }

  }


  @Test
  fun testQuery() {

    val customers =
        Customer.
            where().ilike("name", "rob%")
            .findList();

    assertNotNull(customers);

  }

}

package org.example.service

import com.avaje.ebean.Ebean
import com.avaje.ebean.Transaction
import org.example.domain.Customer
import org.junit.Test

import java.util.List

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

public class InsertCustomerTest extends ExampleBaseTestCase {

  
  @Test
  public void test() {
    
    Customer customer = new Customer(name: 'Rob')

    // insert the customer using extension method
    customer.save()
    
    Customer fetched = Customer.find.byId(customer.id)

    // fetch using the Ebean singleton style
    Customer fetched2 = Ebean.find(Customer, customer.id)
    
    assert "Rob" == fetched.name
    assert "Rob" == fetched2.name
        
  }
  
  
  /**
   * Test showing an explicit transaction.
   */
  @Test
  public void testExplicitTransaction() {
    
    // create a transaction to wrap multiple saves
    Transaction transaction = Customer.db().beginTransaction()
    try {
   
      Customer customer = new Customer(name: 'Roberto')
      customer.save()
   
      Customer otherCustomer = new Customer()
      otherCustomer.name = "Franko"
      otherCustomer.save()
      
      transaction.commit()
      
    } finally {
      // this cleans up the transaction if something 
      // fails in the try block
      transaction.end()
    }
    
  }
  
  
  /**
   * Test showing an explicit transaction with extra control 
   * the use of jdbc batching.
   */
  @Test
  public void testExplicitTransactionWithBatchControls() {
    
    Transaction transaction = Customer.db().beginTransaction()
    try {
      
      // turn off cascade persist for this transaction
      transaction.setPersistCascade(false)
      
      // extra control over jdbc batching for this transaction
      transaction.setBatchGetGeneratedKeys(false)
      transaction.setBatchMode(true)
      transaction.setBatchSize(20)
      
      Customer customer = new Customer(name: 'Roberto')
      customer.save()

      transaction.setBatchFlushOnQuery(false)
      
      Customer otherCustomer = new Customer(name: 'Franko')
      otherCustomer.save()
      
      transaction.commit()
      
    } finally {
      transaction.end()
    }
    
  }
  
  
  @Test
  public void testQuery() {
    
      List<Customer> customers = 
          Customer.find.
            where().ilike("name", "rob%")
            .findList()
    
      assert customers != null
    
  }
  
}

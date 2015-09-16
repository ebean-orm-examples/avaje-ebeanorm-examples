package org.example.service

import org.example.ExampleBaseTestCase
import org.junit.Test
import org.example.domain.Customer
import org.junit.Assert
import kotlin.test.assertFalse
import kotlin.test.assertNull

/**
 * Created by rob on 30/10/14.
 */
class WithTransactionalTest : ExampleBaseTestCase() {

  @Test fun just_run_it() {

    val load = WithTransactional()
    try {
      load.performInTransaction()
      assertFalse(true)

    } catch (e: RuntimeException) {
      val customer : Customer? = Customer.where().eq("name", "ShouldNotFindMe").findUnique()
      assertNull(customer)
    }

  }
}
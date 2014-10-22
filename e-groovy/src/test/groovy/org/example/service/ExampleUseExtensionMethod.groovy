package org.example.service

import com.avaje.ebean.Ebean
import org.example.domain.Customer
import org.junit.Test

/**
 * Created by rob on 22/10/14.
 */
class ExampleUseExtensionMethod extends ExampleBaseTestCase {

  @Test
  void test_run() {

    // e.g. run a closure wrapped in a transaction scope
    Ebean.run {

      Customer one = new Customer(name:'one')
      one.save();

      Customer two = new Customer(name:'two', registered: new Date())
      two.save()
    }
  }
}

package org.example.service

import org.junit.Test

/**
 */
class LoadCustomerServiceTest extends ExampleBaseTestCase {

  @Test
  void test() {

    LoadCustomerService loadCustomerService = new LoadCustomerService();
    loadCustomerService.loadSome();

  }

}

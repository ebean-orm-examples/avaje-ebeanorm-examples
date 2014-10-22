package org.example.service

import groovy.transform.CompileStatic
import org.example.domain.Customer
import org.junit.Test

@CompileStatic
class ExampleGroovyNullSafeTraversal extends ExampleBaseTestCase {

  @Test
  void test_query_then_navigate() {

    LoadExampleData.load();

    def customers = Customer.find
      .select("name")
      .fetch("billingAddress","city")
      .fetch("billingAddress.country")
      .findList();

    for (Customer customer : customers) {
      // null safe traversal of properties
      def city = customer.billingAddress?.city;
      def country = customer.billingAddress?.country?.name;
      System.println("customer: $customer.name  city: $city  country: $country");
    }

  }
}


package org.example.domain;

import com.avaje.ebean.Ebean;
import org.example.ExampleBaseTestCase;
import org.junit.Test;
import org.example.service.LoadExampleData

public class ExamplePartialObjectQueryTest : ExampleBaseTestCase() {

  Test
  fun test() {

    val customer =
       Customer.find.select("name, email")
            .where().idEq(12)
            .findUnique();

    System.out.println("name: ${customer?.name}");
  }

  Test
  fun automaticallyAddJoins() {

    LoadExampleData().load()

    val country = Country.ref("NZ") //Ebean.getReference(javaClass<Country>(), "NZ");

    val customer =
      Customer.find
        .select("name")
        .where()
          .eq("name","Rob")
          .eq("billingAddress.country", country)
        .findUnique();

    val customerName = customer.name;
    val countryName = customer.billingAddress?.country?.name;

    if (null == customer.billingAddress?.country?.name) {
      System.out.println("got a null");
    }

    System.out.println("name: ${customer.name}  $customerName ... and ${customer.billingAddress?.country} $countryName");

  }
}

package org.example.domain;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.QueryEachConsumer;
import org.example.ExampleBaseTestCase;
import org.example.domain.query.QCustomer;
import org.example.service.LoadExampleData;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ExamplePartialObjectQueryTest extends ExampleBaseTestCase {

  @Test
  public void test() {

    Customer customer =
       Customer.find.where()
            .id.eq(12)
            .select("name, email")
            .findUnique();
  }

  @Test
  public void testDoo() {


    new QCustomer()
        .id.greaterThan(12)
        .name.startsWith("asd")
        .findList();

  }

  @Test
  public void automaticallyAddJoins() {

    LoadExampleData.load();

    Country country = Ebean.getReference(Country.class, "NZ");
    List<Customer> customers = new QCustomer()
        .select("name")
        .billingAddress.country.equalTo(country)
        .orderBy()
          .name.asc()
        .findList();

    List<Customer> customers2 = new QCustomer()
        .select("name")
        .name.contains("asd")
        .billingAddress.country.equalTo(country)
        .order()
          .name.asc()
        .findList();

    List<Customer> customers3
        = new QCustomer()
        .name.startsWith("Rob")
        .registered.before(new Date())
        .or()
          .id.greaterThan(1)
          .and()
            .name.icontains("rob")
            .inactive.isTrue()
          .endAnd()
        .endOr()
        .findList();

    System.out.println();

  }
}

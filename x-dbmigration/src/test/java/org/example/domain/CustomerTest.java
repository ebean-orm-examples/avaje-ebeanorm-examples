package org.example.domain;

import com.avaje.ebean.Ebean;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

  @Test
  public void canInsert() {

    Customer customer = new Customer();
    customer.setName("Rob");
    //customer.setFoo("one");
    customer.save();
//
//    assertThat(customer.getId()).isNotNull();

//    Customer rob = Ebean.find(Customer.class)
//        .where().eq("name", "Rob")
//        .findUnique();
//
//    rob.setShortDesc("cone");
//    Ebean.save(rob);

//    long now = System.currentTimeMillis();
//    Timestamp ts = new Timestamp(now - 1000*60*10);
//
//    List<Customer> list = Ebean.find(Customer.class)
//        .asOf(ts)
//        .findList();
//
//    System.out.print("asd");

  }

}
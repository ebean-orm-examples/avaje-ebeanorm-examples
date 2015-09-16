package org.example.domain;

import com.avaje.ebean.DelegateEbeanServer;
import com.avaje.ebean.MockiEbean;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CustomerTest {

  @Test
  public void updateJim() {

    Customer jim = Customer.find.byName("jim");
    //jim.setComments("another update");
    jim.save();
  }

  @Test
  public void insert() {


    DelegateEbeanServer mock = new DelegateEbeanServer();
    mock.withPersisting(true);

    MockiEbean.runWithMock(mock, () -> {

      Customer customer = new Customer();
      customer.setName("jim");
      //customer.setComments("first comment");

      customer.save();

      //customer.setComments("second comment");
      customer.save();

    });

    assertThat(mock.capturedBeans.save).hasSize(2);

  }

}
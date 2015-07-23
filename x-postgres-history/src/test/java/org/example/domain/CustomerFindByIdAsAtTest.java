package org.example.domain;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Query;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalUnit;

import static org.assertj.core.api.Assertions.*;

public class CustomerFindByIdAsAtTest {

  @Test
  public void test() {

//    LocalDateTime asAt = LocalDateTime.now();
//    LocalDateTime localDateTime = asAt.minusHours(2);

    //localDateTime.

    long epochMilli = OffsetDateTime.now().minusHours(3).toInstant().toEpochMilli();
    Timestamp asAt = new Timestamp(epochMilli);

    System.out.println("---- "+asAt);

    Query<Customer> query = Customer.find.query();
    query.where().eq("name", "jim");
    query.setHistoryAsAtValue(asAt);//new Timestamp(System.currentTimeMillis()));

    Customer customer = query.findUnique();
    //Customer.find.byName("rob");

    System.out.println("customer: "+customer);

    //assertThat(customer).isNotNull();

  }
}

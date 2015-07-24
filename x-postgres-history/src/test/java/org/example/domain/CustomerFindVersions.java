package org.example.domain;

import com.avaje.ebean.Version;
import org.avaje.ebeantest.LoggedSql;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CustomerFindVersions {

  @Test
  public void test_queryAllVersions() {

    LoggedSql.start();

    List<Version<Customer>> customerVersions =
        Customer.find.query()
            .where()
            .idEq(1)
            .findVersions();

    List<String> loggedSql = LoggedSql.stop();
    assertThat(loggedSql).hasSize(1);

    assertThat(customerVersions).isNotNull();

    String sqlSelect = loggedSql.get(0);


    // select clause prefixed with lower and upper bounds
    assertThat(sqlSelect.contains("select lower(t0.sys_period) as c0, upper(t0.sys_period) as c1,")).isTrue();

    // from the with_history view
    assertThat(sqlSelect.contains(" from customer_with_history t0 ")).isTrue();

    // appends order by lower bound desc
    assertThat(sqlSelect.contains(" order by t0.id, lower(t0.sys_period) desc")).isTrue();

  }
}

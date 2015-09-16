package org.example.domain;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.ValuePair;
import com.avaje.ebean.Version;
import org.avaje.ebeantest.LoggedSql;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class CustomerFindVersions {

  @Ignore
  @Test
  public void oracle_setClientInfo() throws SQLException {


    Transaction txn = Ebean.beginTransaction();
    try {
      Connection connection = txn.getConnection();

      connection.setClientInfo("OCSID.CLIENTID","appUserIdRob");
      connection.setClientInfo("OCSID.ECID", "myIpAddress");

      PreparedStatement stmt = connection.prepareStatement("select id, name from customer where id = ?");
      stmt.setLong(1, 29);

      ResultSet resultSet = stmt.executeQuery();
      resultSet.close();


    } finally {
      txn.commit();
    }

  }

  @Ignore
  @Test
  public void oracle_findVersionsBetween() throws SQLException {

    LoggedSql.start();

    long epochStart = OffsetDateTime.now().minusMinutes(250).toInstant().toEpochMilli();
    Timestamp start = new Timestamp(epochStart);

    long epochEnd = OffsetDateTime.now().minusMinutes(3).toInstant().toEpochMilli();
    Timestamp end = new Timestamp(epochEnd);

    List<Version<Customer>> customerVersions =
        Customer.find.query()
            .where()
            .idEq(21)
            .findVersionsBetween(start, end);

    for (Version<Customer> customerVersion : customerVersions) {
      Customer bean = customerVersion.getBean();
      Map<String, ValuePair> diff = customerVersion.getDiff();
      Timestamp effectiveStart = customerVersion.getStart();
      Timestamp effectiveEnd = customerVersion.getEnd();

      assertThat(bean).isNotNull();
      assertThat(diff).isNotNull();
      assertThat(effectiveStart).isNotNull();
      //assertThat(effectiveEnd).isNotNull();
    }


    List<String> loggedSql = LoggedSql.stop();
    assertThat(loggedSql).hasSize(1);

    assertThat(customerVersions).isNotNull();

    String sqlSelect = loggedSql.get(0);

    // select clause prefixed with lower and upper bounds
    assertThat(sqlSelect.contains("select versions_starttime c0, versions_endtime c1,")).isTrue();

    // from the with_history view
    assertThat(sqlSelect.contains(" from customer versions between timestamp ? and ? t0 where t0.id = ?")).isTrue();

    // appends order by lower bound desc
    assertThat(sqlSelect.contains(" order by t0.id, versions_starttime desc")).isTrue();
  }

  @Test
  public void postgres_findVersionsBetween() {

    long epochStart = OffsetDateTime.now().minusDays(50).toInstant().toEpochMilli();
    Timestamp start = new Timestamp(epochStart);

    long epochEnd = OffsetDateTime.now().minusMinutes(3).toInstant().toEpochMilli();
    Timestamp end = new Timestamp(epochEnd);

    LoggedSql.start();

    List<Version<Customer>> customerVersions =
        Customer.find.query()
            .where()
            .idEq(1)
            .findVersionsBetween(start, end);

    List<String> loggedSql = LoggedSql.stop();
    assertThat(loggedSql).hasSize(1);

    assertThat(customerVersions).isNotNull();

    String sqlSelect = loggedSql.get(0);

    // select clause prefixed with lower and upper bounds
    assertThat(sqlSelect.contains("select lower(t0.sys_period) c0, upper(t0.sys_period) c1,")).isTrue();

    // from the with_history view
    assertThat(sqlSelect.contains(" from customer_with_history t0 ")).isTrue();

    assertThat(sqlSelect.contains(" and lower(t0.sys_period) > ?  and lower(t0.sys_period) < ? ")).isTrue();

    // appends order by lower bound desc
    assertThat(sqlSelect.contains(" order by t0.id, lower(t0.sys_period) desc")).isTrue();
  }

  @Test
  public void postgres_queryAllVersions() {

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

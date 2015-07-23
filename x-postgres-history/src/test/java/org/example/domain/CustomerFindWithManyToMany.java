package org.example.domain;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import org.avaje.ebeantest.LoggedSql;
import org.example.domain.excludem2m.ContactExcludeM2M;
import org.example.domain.excludem2m.CustomerExcludeM2M;
import org.example.domain.excludem2m.FeatureExcludeM2M;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerFindWithManyToMany {


  @Test
  public void findAsOf_given_intersectionWithHistory_then_joinAndEffectiveDateAppliedToIntersection() {

    long epochMilli = OffsetDateTime.now().minusHours(3).toInstant().toEpochMilli();
    Timestamp asOf = new Timestamp(epochMilli);

    LoggedSql.start();

    Customer.find.query()
        .asOf(asOf)
        .fetch("features")
        .where().eq("name", "jack")
        .findUnique();

    List<String> loggedSql = LoggedSql.stop();
    assertThat(loggedSql).hasSize(1);

    String sqlSelect = loggedSql.get(0);

    assertThat(sqlSelect.contains(" from customer_with_history t0 ")).isTrue();
    assertThat(sqlSelect.contains(" join customer_feature_with_history t1z_ ")).isTrue();
    assertThat(sqlSelect.contains(" t0.sys_period @> ?::timestamptz and t1z_.sys_period @> ?::timestamptz")).isTrue();
    assertThat(sqlSelect.contains(" --bind(jack asOf ")).isTrue();

  }


  @Test
  public void findAsOf_given_intersectionWithNoHistory_then_joinNormalAndNoEffectiveDatePredicate() {

    ServerConfig serverConfig = new ServerConfig();
    serverConfig.setName("pg");
    serverConfig.setDefaultServer(true);
    serverConfig.loadFromProperties();
    serverConfig.addClass(CustomerExcludeM2M.class);
    serverConfig.addClass(FeatureExcludeM2M.class);
    serverConfig.addClass(ContactExcludeM2M.class);
    serverConfig.addClass(Address.class);
    serverConfig.addClass(Country.class);

    EbeanServer localServer = EbeanServerFactory.create(serverConfig);

    long epochMilli = OffsetDateTime.now().minusHours(3).toInstant().toEpochMilli();
    Timestamp asOf = new Timestamp(epochMilli);

    LoggedSql.start();

    localServer.find(CustomerExcludeM2M.class)
        .asOf(asOf)
        .fetch("features")
        .where().eq("name", "jack")
        .findUnique();

    List<String> loggedSql = LoggedSql.stop();
    assertThat(loggedSql).hasSize(1);

    String sqlSelect = loggedSql.get(0);

    assertThat(sqlSelect.contains(" --bind(jack asOf ")).isTrue();
    assertThat(sqlSelect.contains(" from customer_with_history t0 ")).isTrue();
    assertThat(sqlSelect.contains(" join customer_feature t1z_ ")).isTrue();
    assertThat(sqlSelect.contains(" t0.sys_period @> ?::timestamptz")).isTrue();
    assertThat(!sqlSelect.contains(" t1z_.sys_period @> ?::timestamptz")).isTrue();

  }

}

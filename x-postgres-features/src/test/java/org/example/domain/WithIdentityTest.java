package org.example.domain;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.config.dbplatform.DatabasePlatform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import org.example.ExampleBaseTestCase;
import org.junit.Test;

public class WithIdentityTest extends ExampleBaseTestCase {

  @Test
  public void test() {

    WithIdentity bean = new WithIdentity();
    bean.setName("foo");

    SpiEbeanServer defaultServer = (SpiEbeanServer)Ebean.getDefaultServer();
    DatabasePlatform databasePlatform = defaultServer.getDatabasePlatform();

    Ebean.save(bean);
  }
}
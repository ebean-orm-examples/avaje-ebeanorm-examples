package org.example;

import com.avaje.ebean.Ebean;
import org.junit.Test;

public class GenerateMigrationTest extends ExampleBaseTestCase {

  @Test
  public void generate() {

    System.setProperty("ddl.migration.generate", "true");
    System.setProperty("ddl.migration.version", "1.2.2_4");
    System.setProperty("ddl.migration.name", "foo");

    // migration will be run on EbeanServer instance start
    Ebean.getDefaultServer();

  }
}

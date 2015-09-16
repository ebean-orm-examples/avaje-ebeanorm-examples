package main;

import com.avaje.ebean.config.dbplatform.DbPlatformName;
import com.avaje.ebean.dbmigration.DbMigration;

import java.io.IOException;

public class MainDbMigration {

  public static void main(String[] args) throws IOException {

    DbMigration migration = new DbMigration();
    migration.setPathToResources("src/main/resources");
    migration.setPlatform(DbPlatformName.POSTGRES);

    migration.generateMigration();

  }

  private static void runMultiplePlatforms() throws IOException {

    DbMigration migration = new DbMigration();
    migration.setPathToResources("src/main/resources");
    migration.addPlatform(DbPlatformName.POSTGRES, "pg");
    migration.addPlatform(DbPlatformName.MYSQL, "mysql");

    migration.generateMigration();
  }
}

package main;

import com.avaje.ebean.dbmigration.DbMigration;

import java.io.IOException;

public class MainDbMigration {

  public static void main(String[] args) throws IOException {

    DbMigration dbMigration = new DbMigration();
    //dbMigration.setPathToResources("src/main/resources");

    //dbMigration.setPlatform(DbPlatformName.POSTGRES);
    dbMigration.generateMigration();

  }
}

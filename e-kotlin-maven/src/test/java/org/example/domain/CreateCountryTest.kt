package org.example.domain

import com.avaje.ebean.Ebean
import org.example.ExampleBaseTestCase
import org.junit.Test
import kotlin.reflect.jvm.java

class CreateCountryTest : ExampleBaseTestCase() {

    Test fun doInsert() {

        var sa = Country(code = "SA", name = "South Af");
        sa.save();

        //sa = Country(code="SA", name="South Af");
        //sa.save();

        val mutableList = Country.all();
        mutableList.size();

        //Country.db().save(null);

        //val any = Country.db().getServerCacheManager().getBeanCache(Country.javaClass).get("NZ")
        //any.toString();

        val country = Country.byId("NZ")
        country.name = "something"
        country.save();

      val ebeanServer = Ebean.getDefaultServer();

      val nzC = Ebean.find(Country::class.java, "NZ")
      nzC.name = "something"

      ebeanServer.find(Country.javaClass, "NZ");


        val nzUni = Country.where().findUnique()
        nzUni.code;

        val nz = Country.byId("NZ")
        nz.code;

    }

}
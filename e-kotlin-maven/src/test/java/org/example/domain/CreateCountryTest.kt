package org.example.domain

import org.example.ExampleBaseTestCase
import org.junit.Test

/**
 * Created by rob on 19/12/14.
 */

class CreateCountryTest : ExampleBaseTestCase() {

    Test fun doInsert() {

        var sa = Country(code="SA", name="South Af");
        sa.save();

        //sa = Country(code="SA", name="South Af");
        //sa.save();

    }

}
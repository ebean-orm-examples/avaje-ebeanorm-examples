package org.example.service

import org.junit.Test
import org.example.ExampleBaseTestCase

/**
 * Created by rob on 23/10/14.
 */
class LoadExampleDataTest : ExampleBaseTestCase() {

  @Test fun just_run_it() {

    val load = LoadExampleData()
    load.load()

  }
}
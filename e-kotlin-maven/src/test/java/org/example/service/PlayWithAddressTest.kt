package org.example.service

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by rob on 29/05/15.
 */
class PlayWithAddressTest {

    @Test
    fun testFindAll() {

        val play = PlayWithAddress()
        val addresses = play.findAll();

        assertEquals(0, addresses?.size())
    }
}
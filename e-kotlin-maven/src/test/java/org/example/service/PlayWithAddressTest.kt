package org.example.service

import org.junit.Test
import kotlin.test.assertEquals

class PlayWithAddressTest {

    @Test
    fun testFindAll() {

        val play = PlayWithAddress()
        val addresses = play.findAll();

        assertEquals(0, addresses?.size)
    }
}
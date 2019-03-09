package com.madukubah.alan.footballclubfinal.config

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class ConfigKtTest {

    @Test
    fun testdateToString() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wed, 28 Feb 2018", dateToString(date))
    }
}
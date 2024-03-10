package com.kueski.marktest.business.manager

import com.kueski.marktest.helpers.getNowMinus
import com.kueski.marktest.helpers.round
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ExtensionFunctionTest {

    @Test
    fun testRoundFloatCeil() {
        val value = 2.1289412f
        Assert.assertEquals(2.13f, value.round(2))
    }

    @Test
    fun testRoundFloatDown() {
        val value = 2.1239412f
        Assert.assertEquals(2.12f, value.round(2))
    }

    @Test
    fun testGetNowMinusDays() {
        val now = LocalDate.now().minusDays(3).format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        )
        Assert.assertEquals(now, getNowMinus(3))
    }
}
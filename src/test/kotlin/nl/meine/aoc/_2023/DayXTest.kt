package nl.meine.aoc._2023

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayXTest {
    private val instance  = DayX()

    private val input1 = """"""


    @Test
    fun one() {
        assertEquals(8, instance.one(input1))
    }

    @Test
    fun two() {
        assertEquals(2286, instance.two(input1))
    }
}
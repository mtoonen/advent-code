package nl.meine.aoc._2023

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day6Test {
    private val instance  = Day6()

    private val inputTime = listOf(7,15,30)
    private val distance = listOf(9,  40,  200)


    @Test
    fun one() {
        assertEquals(288, instance.one(inputTime,distance))
    }

    @Test
    fun two() {
        assertEquals(71503, instance.two(71530,940200))
    }
}
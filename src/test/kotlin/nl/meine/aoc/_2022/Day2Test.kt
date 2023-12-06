package nl.meine.aoc._2022

import nl.meine.aoc._2022.Day2
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day2Test {

    val instance = Day2();
    @BeforeEach
    fun setUp() {
    }

    val input = """A Y
B X
C Z"""

    @Test
    fun one() {
        assertEquals(15, instance.one(input))
    }

    @Test
    fun two() {
        assertEquals(12, instance.two(input))
    }
}
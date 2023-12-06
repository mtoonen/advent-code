package nl.meine.aoc._2022

import nl.meine.aoc._2022.Day1
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day1Test {

    lateinit var instance : Day1
    val input = """1000
2000
3000

4000

5000
6000

7000
8000
9000

10000"""
    @BeforeEach
    fun setUp() {
        instance = Day1()
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun one() {

        assertEquals(24000, instance.one(input))
    }


    @Test
    fun two() {

        assertEquals(45000, instance.two(input))
    }
}
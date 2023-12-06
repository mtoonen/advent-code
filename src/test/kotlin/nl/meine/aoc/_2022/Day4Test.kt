package nl.meine.aoc._2022

import nl.meine.aoc._2022.Day4
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day4Test {

    val instance : Day4 = Day4()
    @Test
    fun one() {
        assertEquals(2, instance.one(input))
    }

    @Test
    fun two() {
        assertEquals(4, instance.two(input))
    }

    val input : String = """2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8"""
}
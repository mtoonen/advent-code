package nl.meine.aoc._2023

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day1Test {
    private val instance : Day1 = Day1()

    private val input1 = """1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet"""

    private val input2 ="""two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen"""
    @Test
    fun one() {
        assertEquals(142, instance.one(input1))
    }

    @Test
    fun two() {
        assertEquals(281, instance.two(input2))
    }
}
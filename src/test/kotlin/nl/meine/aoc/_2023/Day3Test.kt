package nl.meine.aoc._2023

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day3Test {
    private val instance : Day3 = Day3()

    private val input1 =
        """467..114..
..*.......
..35..633.
........*.
617*......
.......58.
.*592.....
......755.
$...*....*
.664.598.."""
//linksboven, rechtsboven,
//linksonder,rechtsonder
// rechts,links
    // onderstart,ondereind
    // govenstart,boveneind,

    private val input2 ="""12.......*..
+.........34
.......-12..
..78........
..*....60...
78..........
.......23...
....90*12...
............
2.2......12.
.*.........*
1.1.......56"""
    @Test
    fun one() {
        assertEquals(4361, instance.one(input1))
    }

    @Test
    fun one2() {
        assertEquals(413, instance.one(input2))
    }

    @Test
    fun two() {
        assertEquals(2286, instance.two(input1))
    }
}
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


    val inputTwo ="""467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598.."""
    val inputtwo2="""12.......*..
+.........34
.......-12..
..78........
..*....60...
78.........9
.5.....23..$
8...90*12...
............
2.2......12.
.*.........*
1.1..503+.56"""
    @Test
    fun two() {
        assertEquals(467835, instance.two(inputTwo))
    }

    @Test
    fun two2() {
        assertEquals(6756, instance.two(inputtwo2))
    }


    val inputCorners="""*2...2*
2.....2
.......
2.....2
*2...2*"""


    val inputCornersInverted="""2.....2
2*...*2
.......
.......
2*...*2
2.....2"""

    val inputCornersInverted2="""2*...*2
2.....2
.......
.......
2.....2
2*...*2"""
    val inputCornersInverted3="""2*...*2
2.....2
.......
.......
.......
2.....2
2*...*2"""

    @Test
    fun twoCorners() {
        assertEquals(16, instance.two(inputCorners))
    }
    @Test
    fun twoCornersInverted() {
        assertEquals(16, instance.two(inputCornersInverted))
        assertEquals(16, instance.two(inputCornersInverted2))
    }

    @Test
    fun testCornersFail(){
        assertEquals(16, instance.two(inputCornersInverted3))

    }
}
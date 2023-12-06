package nl.meine.aoc._2022

import nl.meine.aoc._2022.Day5
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day5Test {

    val ins = Day5();
    val start = """    
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 """

    val instruction = """move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2"""
    @Test
    fun one() {
        assertEquals("CMZ", ins.one(start, instruction))
    }

    @Test
    fun two() {
        assertEquals("MCD", ins.two(start, instruction))
    }
}
package nl.meine.aoc._2023

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day5Test {
    private val instance= Day5()

    private val seeds = "79 14 55 13"

    private val seed2soil = """50 98 2
52 50 48"""

private val soil2fertilizer="""0 15 37
37 52 2
39 0 15"""

    private val fertilizer2water =
    """49 53 8
0 11 42
42 0 7
57 7 4"""

    private val water2light =
    """88 18 7
18 25 70"""

    private val light2temperature =
    """45 77 23
81 45 19
68 64 13"""

    private val temperature2humidity =
    """0 69 1
1 0 69"""

    private val humidity2location =
    """60 56 37
56 93 4"""


    @Test
    fun one() {
        assertEquals(35, instance.one(seeds,seed2soil,soil2fertilizer,fertilizer2water,water2light,light2temperature,temperature2humidity,humidity2location ))
    }

    @Test
    fun two() {
        assertEquals(46, instance.two(seeds,seed2soil,soil2fertilizer,fertilizer2water,water2light,light2temperature,temperature2humidity,humidity2location ))
    }

    @Test
    fun parseString() {
        val result = instance.parseString(seed2soil)
        assertEquals(instance.getAnswer(result,79),81)
        assertEquals(instance.getAnswer(result,14),14)
        assertEquals(instance.getAnswer(result,55),57)
        assertEquals(instance.getAnswer(result,13),13)
    }

    @Test
    fun parseLine() {
    }
}
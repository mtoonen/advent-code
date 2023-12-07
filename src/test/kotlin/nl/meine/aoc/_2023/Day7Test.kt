package nl.meine.aoc._2023

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day7Test {
    private val instance  = Day7()

    private val input1 = """32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483"""


    private val input1Sorttest =
"""22222 5
33222 4
T55J5 3
22334 2
32T3K 1"""


    private val input1SorttestSmall =
        """22334 2
32T3K 1"""

    @Test
    fun testSorting(){
        val hands = instance.createHands(input1)
        val ordered = instance.orderHands(hands)
        assertEquals(765, ordered[0].bid)
        assertEquals(220, ordered[1].bid)
        assertEquals(28, ordered[2].bid)
        assertEquals(684, ordered[3].bid)
        assertEquals(483, ordered[4].bid)
    }

    @Test
    fun testSorting2(){
        val hands = instance.createHands(input1Sorttest)
        val ordered = instance.orderHands(hands)
        assertEquals(1, ordered[0].bid)
        assertEquals(2, ordered[1].bid)
        assertEquals(3, ordered[2].bid)
        assertEquals(4, ordered[3].bid)
        assertEquals(5, ordered[4].bid)
    }

    @Test
    fun testSorting2Small(){
        val hands = instance.createHands(input1SorttestSmall)
        val ordered = instance.orderHands(hands)
        assertEquals(1, ordered[0].bid)
        assertEquals(2, ordered[1].bid)
    }
    @Test
    fun one() {
        assertEquals(6440, instance.one(input1))
    }

    @Test
    fun checkCardRetrieve (){
        assertEquals(Card.A, Card.getCard("A"))
        assertEquals(Card.J, Card.getCard("J"))
        assertEquals(Card._2, Card.getCard("2"))
    }

    @Test
    fun two() {
        assertEquals(5905, instance.two(input1))
    }
}
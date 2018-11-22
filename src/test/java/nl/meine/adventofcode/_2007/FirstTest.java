package nl.meine.adventofcode._2007;

import  org.junit.*;

import static org.junit.Assert.assertEquals;

public class FirstTest {

    First instance = new First();


/*

1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and the third digit (2) matches the fourth digit.
1111 produces 4 because each digit (all 1) matches the next.
1234 produces 0 because no digit matches the next.
91212129 produces 9 because the only digit that matches the next one is the last digit, 9.
 */



    @Test
    public void testSum1(){
        String input = "1122";
        int real = instance.sum(input);
        assertEquals(3, real);
    }
    @Test
    public void testSum2(){
        String input = "1111";
        int real = instance.sum(input);
        assertEquals(4, real);
    }
    @Test
    public void testSum3(){
        String input = "1234";
        int real = instance.sum(input);
        assertEquals(0, real);
    }
    @Test
    public void testSum4(){
        String input = "91212129";
        int real = instance.sum(input);
        assertEquals(9, real);
    }
/**
 * 1212 produces 6: the list contains 4 items, and all four digits match the digit 2 items ahead.
 * 1221 produces 0, because every comparison is between a 1 and a 2.
 * 123425 produces 4, because both 2s match each other, but no other digit has a match.
 * 123123 produces 12.
 * 12131415 produces 4.
 */

    @Test
    public void testSum5() {
        String input = "1212";
        int real = instance.sumSameHalf(input);
        assertEquals(6, real);
    }

    @Test
    public void testSum6() {
        String input = "1221";
        int real = instance.sumSameHalf(input);
        assertEquals(0, real);
    }

    @Test
    public void testSum7() {
        String input = "123425";
        int real = instance.sumSameHalf(input);
        assertEquals(4, real);
    }

    @Test
    public void testSum8() {
        String input = "123123";
        int real = instance.sumSameHalf(input);
        assertEquals(12, real);
    }

    @Test
    public void testSum9() {
        String input = "12131415";
        int real = instance.sumSameHalf(input);
        assertEquals(4, real);
    }

}

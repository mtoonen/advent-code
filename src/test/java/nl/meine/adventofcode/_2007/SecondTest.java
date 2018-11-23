/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.meine.adventofcode._2007;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Meine Toonen
 */
public class SecondTest {
    
    public SecondTest() {
    }

    private Second instance = new Second();
    
    @Test
    public void testChecksum() {
        String[] input = 
{"5 1 9 5",
"7 5 3",
"2 4 6 8"};
        int real = instance.checksumMinMax(input);
        assertEquals(18, real);
    }

    @Test
    public void testDifferenceRow1() {
        String row = "5 1 9 5";
        int real = instance.differenceRow(row);
        assertEquals(8,real);
    }

    @Test
    public void testDifferenceRow2() {
        String row = "7 5 3";
        int real = instance.differenceRow(row);
        assertEquals(4,real);
    }

    @Test
    public void testDifferenceRow3() {
        String row = "2 4 6 8";
        int real = instance.differenceRow(row);
        assertEquals(6,real);
    }
    
    @Test
    public void testChecksumNR() {
        String[] input = {
            "5 9 2 8",
            "9 4 7 3",
            "3 8 6 5"
        };/*
{"5 1 9 5",
"7 5 3",
"2 4 6 8"};*/
        int real = instance.checkSumNoRemainder(input);
        assertEquals(9, real);
    }

    @Test
    public void testDifferenceRowNR1() {
        String row = "5 9 2 8";
        int real = instance.differenceRowNoRemainder(row);
        assertEquals(4,real);
    }

    @Test
    public void testDifferenceRowNR2() {
        String row = "9 4 7 3";
        int real = instance.differenceRowNoRemainder(row);
        assertEquals(3,real);
    }

    @Test
    public void testDifferenceRowNR3() {
        String row = "3 8 6 5";
        int real = instance.differenceRowNoRemainder(row);
        assertEquals(2,real);
    }
    
    
    
}

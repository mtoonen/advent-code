package nl.meine.adventofcode._2021;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    Day3 instance;
    List<String> input;

    @BeforeEach
    void setup(){
        instance = new Day3();
        input = new ArrayList<>();
        input.add("00100");
        input.add("11110");
        input.add("10110");
        input.add("10111");
        input.add("10101");
        input.add("01111");
        input.add("00111");
        input.add("11100");
        input.add("10000");
        input.add("11001");
        input.add("00010");
        input.add("01010");
    }

    @Test
    void one() {
        assertEquals(198, instance.one(input));
    }

    @Test
    void two() {
        assertEquals(230, instance.two(input));
    }

    @Test
    void main() {
    }

    @Test
    void testOne() {
    }

    @Test
    void getNumberOfBit() {
        List<Integer> column = Arrays.asList(new Integer[]{0,0,0,1});
        assertEquals(3,instance.getNumberOfBit(0, column));
        assertEquals(1,instance.getNumberOfBit(1, column));
    }

    @Test
    void getColumn() {
        List<Integer> column = Arrays.asList(new Integer[]{0,1,1,1,1,0,0,1,1,1,0,0});
        List<List<Integer>> columns = instance.parseInput(input);
        assertEquals(column, instance.getColumn(columns, 0));
    }

    @Test
    void parseInput() {
        List<List<Integer>> output = instance.parseInput(input);
        assertEquals(5, output.size());
        for (List<Integer> column: output) {
            assertEquals(12, column.size());
        }

    }

    @Test
    void filterMost() {
        List<List<Integer>> parsed = instance.parseInput(input);
        List<List<Integer>> output = instance.filter(parsed, 0, true);
        assertEquals(7, output.get(0).size());
        List<List<Integer>> output2 = instance.filter(output, 1, true);
        assertEquals(4, output2.get(0).size());
    }
    @Test
    void filterLeast() {
        List<List<Integer>> parsed = instance.parseInput(input);
        List<List<Integer>> output = instance.filter(parsed, 0, false);
        assertEquals(5, output.get(0).size());
        List<List<Integer>> output2 = instance.filter(output, 1, false);
        assertEquals(2, output2.get(0).size());
    }

    @Test
    void filter() {
    }

    @Test
    void determineBit() {
        List<List<Integer>> parsed = instance.parseInput(input);
        assertEquals(1, instance.determineBit(parsed, 0, true));
        assertEquals(0, instance.determineBit(parsed, 0, false));
    }
}

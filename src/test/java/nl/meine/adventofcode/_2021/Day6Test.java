package nl.meine.adventofcode._2021;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    private String input = "3,4,3,1,2";
    private Day6 instance;

    @BeforeEach
    void init(){
        instance = new Day6();
    }
    @Test
    void one() {
        assertEquals(5934,instance.one(input));
    }
    @Test
    void two() {
        assertEquals(26984457539L,instance.two(input));
    }

    @Test
    void step1() {
        Map<Integer, Long> map = instance.getMap(input);
        String resString = "2,3,2,0,1";
        Map<Integer, Long> expectedMap = instance.getMap(resString);

        Map<Integer, Long> resultMap = instance.step(map);

        assertTrue(expectedMap.equals(resultMap));

    }

    @Test
    void step2() {
        String inpString = "2,3,2,0,1";
        Map<Integer, Long> map = instance.getMap(inpString);
        String resString = "1,2,1,6,0,8";
        Map<Integer, Long> expectedMap = instance.getMap(resString);

        Map<Integer, Long> resultMap = instance.step(map);

        assertTrue(expectedMap.equals(resultMap));

    }

    @Test
    void getMap() {
        Map<Integer, Long> result = instance.getMap(input);
        assertEquals(1, result.get(1));
        assertEquals(1, result.get(2));
        assertEquals(2, result.get(3));
        assertEquals(1, result.get(4));
        assertEquals(0, result.get(5));
        assertEquals(0, result.get(6));
        assertEquals(0, result.get(7));
        assertEquals(0, result.get(8));
        assertEquals(0, result.get(0));
        assertEquals(9, result.keySet().size());
    }


    @Test
    void main() {
    }
}

package nl.meine.advent.dayone;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static nl.meine.advent.dayone.Day8.sort;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day8Test {
    Day8 instance;

    List<String> linesString = null;

    Signal s;

    @BeforeEach
    void setUp() throws IOException {
        instance = new Day8();
        InputStream is = Day8.class.getClassLoader().getResourceAsStream("inputday8.txt");
        linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);
        s = new Signal(List.of("acedgfb","cdfbe","gcdfa","fbcad","dab","cefabd","cdfgeb","eafb","cagedb","ab"),List.of("cdfeb","fcadb","cdfeb","cdbaf"));
    }

    @Test
    void one() throws IOException {
        assertEquals(26, instance.one(linesString));
    }

    @Test
    void two() throws IOException {
        assertEquals(61229, instance.two(linesString));
    }

    @Test
    void parseSignals() {
        assertEquals(10, instance.parseSignals(linesString).size());
    }

    @Test
    void parseSignal() {
        String input = "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce";
        Signal s = instance.parseSignal(input);
        assertEquals(List.of("fgae", "cfgab","fg", "bagce"), s.output);
        assertEquals(List.of("gcafb", "gcf", "dcaebfg", "ecagb", "gf", "abcdeg", "gaef", "cafbge", "fdbac", "fegbdc"), s.signal);
    }

    @Test
    void countOccurences() {
        List<Integer> known = List.of(1,2,3);
        List<String> input = List.of("a", "a", "cdcd", "bb");
        assertEquals(3, instance.countOccurences(known, input));
    }

    @Test
    void compute1478() {
        Map<Integer, String> results = new HashMap<>();
        instance.compute1478(results, s);
        assertEquals(sort("ab"),results.get(1));
        assertEquals(sort("eafb"),results.get(4));
        assertEquals(sort("dab"),results.get(7));
        assertEquals(sort("acedgfb"),results.get(8));
    }

    @Test
    void compute3() {
        Map<Integer, String> results = new HashMap<>();
        instance.compute1478(results, s);
        instance.compute3(results, s);
        assertEquals(sort("fbcad"),results.get(3));
    }

    @Test
    void compute6() {
        Map<Integer, String> results = new HashMap<>();
        instance.compute1478(results, s);
        instance.compute3(results, s);
        instance.compute6(results, s);
        assertEquals(sort("cdfgeb"),results.get(6));
    }

    @Test
    void compute5() {
        Map<Integer, String> results = new HashMap<>();
        instance.compute1478(results, s);
        instance.compute3(results, s);
        instance.compute6(results, s);
        instance.compute5(results, s);
        assertEquals(sort("cdfeb"),results.get(5));
    }
    @Test
    void compute9() {
        Map<Integer, String> results = new HashMap<>();
        instance.compute1478(results, s);
        instance.compute3(results, s);
        instance.compute6(results, s);
        instance.compute5(results, s);
        instance.compute9(results, s);
        assertEquals(sort("abcdef"),results.get(9));
    }

    @Test
    void deduce0() {
        Map<Integer, String> results = new HashMap<>();
        instance.compute1478(results, s);
        instance.compute3(results, s);
        instance.compute6(results, s);
        instance.compute5(results, s);
        instance.compute9(results, s);
        instance.deduce0(results, s);
        assertEquals(sort("abcdeg"),results.get(0));
    }

    @Test
    void deduce2() {
        Map<Integer, String> results = new HashMap<>();
        instance.compute1478(results, s);
        instance.compute3(results, s);
        instance.compute6(results, s);
        instance.compute5(results, s);
        instance.deduce0(results, s);
        instance.deduce2(results, s);
        assertEquals(sort("gcdfa"),results.get(2));
    }

    @Test
    void compare() {
        String one = "abcdef";
        String two = "fedcba";
        assertTrue(instance.compare(one, two));
    }

    @Test
    void determineOutput() {
        Map<Integer, String> results = new HashMap<>();
        instance.compute1478(results, s);
        instance.compute3(results, s);
        instance.compute6(results, s);
        instance.compute5(results, s);
        instance.compute9(results, s);
        instance.deduce0(results, s);
        instance.deduce2(results, s);
        Map<String, Integer> inverse = instance.inverse(results);
        assertEquals("5353", instance.determineOutput(inverse, List.of("cdfeb","fcadb", "cdfeb", "cdbaf")));
    }
}


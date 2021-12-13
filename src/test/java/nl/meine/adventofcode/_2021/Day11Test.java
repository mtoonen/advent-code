package nl.meine.adventofcode._2021;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    Day11 instance;
    List<String> largeExample;
    List<String> smallExample = new ArrayList<>();

    @BeforeEach
    void setUp() throws IOException {
        instance = new Day11();
        InputStream is = Day8.class.getClassLoader().getResourceAsStream("inputday11.txt");
        largeExample = IOUtils.readLines(is, StandardCharsets.UTF_8);

        smallExample.add("11111");
        smallExample.add("19991");
        smallExample.add("19191");
        smallExample.add("19991");
        smallExample.add("11111");
    }

    @Test
    void one() {
        assertEquals(1656, instance.one(largeExample));
    }


    @Test
    void two() {
        assertEquals(195, instance.two(largeExample));
    }

    @Test
    void step1() {
        Grid g = instance.parse(smallExample);
        int flashes = instance.step(g);

        int[][] expected = {{3, 4, 5, 4, 3},
                {4, 0, 0, 0, 4},
                {5, 0, 0, 0, 5},
                {4, 0, 0, 0, 4},
                {3, 4, 5, 4, 3}};
        assertArrayEquals(expected, g.energyLevels);
        assertEquals(9, flashes);
    }

    @Test
    void step2() {
        Grid g = instance.parse(smallExample);
        int flashes = instance.step(g);
        int flashes2 = instance.step(g);

        int[][] expected = {
                {4,5,6,5,4},
                {5,1,1,1,5},
                {6,1,1,1,6},
                {5,1,1,1,5},
                {4,5,6,5,4},
        };
        assertArrayEquals(expected, g.energyLevels);
        assertEquals(9, flashes);
    }

    @Test
    void increaseLevels() {
        int[][] expected = {{2, 2, 2, 2, 2},
                {2, 10, 10, 10, 2},
                {2, 10, 2, 10, 2},
                {2, 10, 10, 10, 2},
                {2, 2, 2, 2, 2}};
        Grid g = instance.parse(smallExample);
        instance.increaseLevels(g);
        assertArrayEquals(expected, g.energyLevels);
    }

    @Test
    void flash() {
        List<String> lines = new ArrayList<>();
        lines.add("11111");
        lines.add("19991");
        lines.add("19191");
        lines.add("19991");
        lines.add("11111");
        Grid g = instance.parse(lines);
        int flashes = instance.flash(g, new Pos(1, 1));
        assertEquals(3, g.energyLevels[1][0]);
        assertEquals(3, g.energyLevels[0][1]);
        assertEquals(2, g.energyLevels[0][0]);
        assertEquals(9, flashes);
    }

    @Test
    void main() {
    }

    @Test
    void resetFlashed() {
    }

    @Test
    void testFlash() {
    }

    @Test
    void findNeighbours() {
        List<String> lines = new ArrayList<>();
        lines.add("11111");
        lines.add("19991");
        lines.add("19191");
        lines.add("19991");
        lines.add("11111");
        Grid g = instance.parse(lines);
        assertEquals(3, instance.findNeighbours(g, new Pos(0, 0)).size());
        assertEquals(3, instance.findNeighbours(g, new Pos(0, 4)).size());
        assertEquals(3, instance.findNeighbours(g, new Pos(4, 4)).size());
        assertEquals(3, instance.findNeighbours(g, new Pos(4, 0)).size());
        assertEquals(8, instance.findNeighbours(g, new Pos(1, 1)).size());

    }

    @Test
    void findFlashable() {
    }
}

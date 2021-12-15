package nl.meine.adventofcode._2021;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

    Day15 instance;
    List<String> input;
    List<String> inputExpanded;

    @BeforeEach
    void setUp() throws IOException {
        instance = new Day15();
        InputStream is = Day8.class.getClassLoader().getResourceAsStream("inputday15.txt");
        input = IOUtils.readLines(is, StandardCharsets.UTF_8);
        InputStream is2 = Day8.class.getClassLoader().getResourceAsStream("inputday15_expanded.txt");
        inputExpanded = IOUtils.readLines(is2, StandardCharsets.UTF_8);
    }

    @Test
    void one() {
        assertEquals(40, instance.one(input));
    }


    @Test
    void two() {
        assertEquals(315, instance.two(input));
    }

    @Test
    void expandMaze() {
        Maze m = instance.parseInput(input);
        Maze m2 = instance.parseInput(inputExpanded);
        instance.expandMaze(m);
        assertEquals(m.maze.length, m2.maze.length);
        assertEquals(m.maze[0].length , m2.maze[0].length);
        assertArrayEquals(m2.maze, m.maze);

    }

    @Test
    void copyMaze() {
    }
}

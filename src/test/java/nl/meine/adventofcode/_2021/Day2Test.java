package nl.meine.adventofcode._2021;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    Day2 instance;
    List<String> input = new ArrayList<>();
    @BeforeEach
    void setUp() {
        instance = new Day2();

        input.add("forward 5");
        input.add("down 5");
        input.add("forward 8");
        input.add("up 3");
        input.add("down 8");
        input.add("forward 2");
    }


    @Test
    void parseInput() {
        List<Command> command = instance.parseInput(input);
        assertEquals(6, command.size());
        assertEquals(Direction.FORWARD,command.get(0).dir);
        assertEquals(5,command.get(0).units);
    }

    @Test
    void one() {
        assertEquals(150, instance.one(input));
    }

    @Test
    void two() {
        assertEquals(900, instance.two(input));
    }

    @Test
    void main() {
    }

}

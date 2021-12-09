package nl.meine.adventofcode._2021;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public int one(List<String> input) {
        List<Command> commands = parseInput(input);
        int depth = 0, horizontal = 0;
        for (Command c : commands) {
            switch (c.dir) {
                case FORWARD:
                    horizontal += c.units;
                    break;
                case UP:
                    depth -= c.units;
                    break;
                case DOWN:
                    depth += c.units;
                    break;
            }

        }
        return depth*horizontal;
    }

    public int two(List<String> input) {
        List<Command> commands = parseInput(input);
        int depth = 0, horizontal = 0, aim =0;
        for (Command c : commands) {
            switch (c.dir) {
                case FORWARD:
                    horizontal += c.units;
                    depth += aim * c.units;
                    break;
                case UP:
                    aim -= c.units;
                    break;
                case DOWN:
                    aim += c.units;
                    break;
            }

        }
        return depth*horizontal;
    }

    public List<Command> parseInput(List<String> inputs) {
        List<Command> commands = new ArrayList<>();
        for (String input : inputs) {
            String[] tokens = input.split(" ");
            Direction d = Direction.fromString(tokens[0]);
            int unit = Integer.parseInt(tokens[1]);
            Command c = new Command();
            c.dir = d;
            c.units = unit;
            commands.add(c);
        }
        return commands;
    }


    public static void main(String[] ags) throws IOException {
        Day2 d = new Day2();

        InputStream is = Day1.class.getClassLoader().getResourceAsStream("inputdaytwo.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);

        System.out.println("Output: " + d.two(linesString));
    }
}

class Command {
    public Direction dir;
    public int units;

}

enum Direction {
    FORWARD("forward"),
    UP("up"),
    DOWN("down");

    private String dir;

    Direction(String dir) {
        this.dir = dir;
    }

    public static Direction fromString(String s) {
        for (Direction d : Direction.values()) {
            if (d.dir.equals(s)) {
                return d;
            }
        }
        return null;
    }

}

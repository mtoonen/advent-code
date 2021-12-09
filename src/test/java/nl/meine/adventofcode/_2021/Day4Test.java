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

class Day4Test {
    Day4 instance;
    String inputNumbers = "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1";
    String boards = "";

    @BeforeEach
    void setUp() {
        instance = new Day4();
    }

    @Test
    void one() throws IOException {
        InputStream is = Day4.class.getClassLoader().getResourceAsStream("boards.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);
        assertEquals(4512, instance.one(linesString, inputNumbers));
    }

    @Test
    void two() throws IOException {
        InputStream is = Day4.class.getClassLoader().getResourceAsStream("boards.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);
        assertEquals(1924, instance.two(linesString, inputNumbers));

    }

    @Test
    void main() {
    }

    @Test
    void parseNumbers() {
        List<Integer> numbers = instance.parseNumbers(inputNumbers);
        assertEquals(27, numbers.size());
        assertEquals(9, numbers.get(2));
    }

    @Test
    void sumUncalled() throws IOException {

        InputStream is = Day4.class.getClassLoader().getResourceAsStream("boards.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);
        List<Board> boards = instance.parseBoards(linesString);
        List<Integer> numbers = instance.parseNumbers("7,4,9,5,11,17,23,2,0,14,21,24");
        Board three = boards.get(2);
        for (Integer number: numbers) {
            three.call(number);
        }

        assertEquals(0, three.rowCorrect());
        assertEquals(188, three.sumUnmarked());
    }

    @Test
    void parseBoards() throws IOException {
        InputStream is = Day4.class.getClassLoader().getResourceAsStream("boards.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);

        List<Board> boards = instance.parseBoards(linesString);
        assertEquals(3, boards.size());
        for (Board b: boards) {

            assertEquals(5, b.matrix.length);
            for (int i = 0; i < b.matrix.length; i++) {
                assertEquals(5, b.matrix[i].length);
            }

        }

        Integer[][] matrixBoard2Expected = {
                {3, 15, 0, 2, 22},
                {9, 18, 13, 17, 5},
                {19, 8, 7, 25, 23},
                {20, 11, 10, 24, 4},
                {14, 21, 16, 12, 6}
        };
        assertEquals(matrixBoard2Expected[0][0], boards.get(1).matrix[0][0]);
        assertEquals(matrixBoard2Expected[0][1], boards.get(1).matrix[0][1]);
        assertEquals(matrixBoard2Expected[0][2], boards.get(1).matrix[0][2]);
        assertEquals(matrixBoard2Expected[0][3], boards.get(1).matrix[0][3]);
        assertEquals(matrixBoard2Expected[0][4], boards.get(1).matrix[0][4]);
    }


    @Test
    void testParseNumbers() {
    }


    @Test
    void parseBoard() {
        List<String> lines = new ArrayList<>();
        lines.add("22 13 17 11  0");
        lines.add("8  2 23  4 24");
        lines.add("21  9 14 16  7");
        lines.add("6 10  3 18  5");
        lines.add("1 12 20 15 19");
        Board b = instance.parseBoard(lines);
        assertEquals(5, b.matrix.length);
        assertEquals(5, b.matrix[0].length);
        assertEquals(22, b.matrix[0][0]);
        assertEquals(13, b.matrix[0][1]);
        assertEquals(17, b.matrix[0][2]);
        assertEquals(11, b.matrix[0][3]);
        assertEquals(0, b.matrix[0][4]);
    }

    @Test
    void testOne() {
    }

    @Test
    void testTwo() {
    }

    @Test
    void parseLine() {
        String line = "21  9 14 16  7";
        Integer[] row = instance.parseLine(line);
        assertEquals(5, row.length);
        assertEquals(21, row[0]);
        assertEquals(7, row[4]);
    }
}

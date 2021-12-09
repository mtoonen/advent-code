package nl.meine.adventofcode._2021;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    Day9 instance;

    List<String> input = null;
    Heightmap h = null;

    @BeforeEach
    void setUp() throws IOException {
        instance = new Day9();
        InputStream is = Day8.class.getClassLoader().getResourceAsStream("inputday9.txt");
        input = IOUtils.readLines(is, StandardCharsets.UTF_8);
        h = instance.parseHeightMap(input);
    }

    @Test
    void one() {
        assertEquals(15, instance.one(input));
    }


    @Test
    void parseHeightmap() {
        Heightmap h = instance.parseHeightMap(input);
        assertEquals(10, h.matrix[0].length);
        assertEquals(5, h.matrix.length);
        assertEquals(2, h.matrix[0][0]);
        assertEquals(2,1, h.matrix[0][1]);
        assertEquals(9, h.matrix[0][2]);
        assertEquals(9, h.matrix[0][3]);
        assertEquals(9, h.matrix[0][4]);
        assertEquals(4, h.matrix[0][5]);
        assertEquals(3, h.matrix[0][6]);
        assertEquals(2, h.matrix[0][7]);
        assertEquals(1, h.matrix[0][8]);
        assertEquals(0, h.matrix[0][9]);
    }

    @Test
    void isLowPoint() {
        Heightmap h = instance.parseHeightMap(input);
        int[] row = h.matrix[0];
        int numLow = 0;
        for (int i = 0; i < row.length; i++) {

            numLow += instance.isLowPoint(h, 0, i) ? 1 :0;
        }
        assertEquals(2, numLow);

    }
    @Test
    void findLowPoints() {
        Heightmap h = instance.parseHeightMap(input);
        List<Integer> lp = instance.findLowPoints(h);
        assertEquals(List.of(1,0,5,5),lp);
    }

    @Test
    void findRiskLevels() {
        Heightmap h = instance.parseHeightMap(input);
        List<Integer> lp = instance.findLowPoints(h);
        List<Integer>rl = instance.findRiskLevels(lp);
        assertEquals(List.of(2,1,6,6),rl);
    }

    @Test
    void sumRiskLevels() {
        Heightmap h = instance.parseHeightMap(input);
        List<Integer> lp = instance.findLowPoints(h);
        List<Integer> rl= instance.findRiskLevels(lp);
        assertEquals(15,instance.sumRiskLevels(rl));
    }

    @Test
    void two() {
       assertEquals(1134, instance.two(input));
    }

    @Test
    void main() {
    }

    @Test
    void resetNonNine() {
        Heightmap h = instance.parseHeightMap(input);
        Heightmap result = instance.resetNonNine(h);
        for (int i = 0; i < result.matrix.length; i++) {
            for (int j = 0; j < result.matrix[i].length; j++) {
                int val =result.matrix[i][j];
                assertTrue( val == 9 || val ==0);
            }
        }
    }

    @Test
    void findEmptySpot() {
        h = instance.resetNonNine(h);
        Coord result = instance.findEmptySpot(h);
        assertEquals(0, result.x);
        assertEquals(0, result.y);
    }

    @Test
    void countBasin() {
        h = instance.resetNonNine(h);
        Coord start = new Coord();
        start.x = 0;
        start.y = 0;

        assertEquals(3,instance.countBasin(start, h));
    }

    @Test
    void findThreeLargest() {
        List<Integer> sizes = List.of(9, 10, 22, 1, 3,34 );
        assertEquals(List.of( 34, 22, 10),instance.findThreeLargest(sizes));
    }

    @Test
    void multiplyEtries() {
    }
}

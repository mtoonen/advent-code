package nl.meine.adventofcode._2021;

import com.google.common.base.Splitter;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.StreamSupport;

public class Day11 {

    public int one(List<String> lines) {
        Grid g = parse(lines);
        int flashed = 0;
        for (int i = 0; i < 100; i++) {
            flashed += step(g);
        }
        return flashed;
    }

    public int two(List<String> input){
        Grid g = parse(input);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int flashed = step(g);
            if(flashed == 100){
                return i+1;
            }
        }
        return -1;
    }

    public int step(Grid g) {
        int flashes = 0;
        increaseLevels(g);
        Pos first = findFlashable(g);
        while(first!=null){
            flashes += flash(g, first);
            first = findFlashable(g);
        }
        resetFlashed(g);
        return flashes;
    }

    public void resetFlashed(Grid g) {
        int[][] l = g.energyLevels;
        for (int row = 0; row < l.length; row++) {
            for (int col = 0; col < l[row].length; col++) {
                if (l[row][col] > 9) {
                    l[row][col] = 0;
                    g.flashed[row][col] =false;
                }
            }
        }
    }

    public int flash(Grid g, Pos p) {
        int flashed = 0;
        g.energyLevels[p.row][p.col]++;
        Set<Pos> neighbours = findNeighbours(g, p);
        if (g.energyLevels[p.row][p.col] > 9 && !g.flashed[p.row][p.col]) {
            g.flashed[p.row][p.col] = true;
            flashed++;

            for (Pos nb : neighbours) {
                flashed += flash(g, nb);
            }
        }
        return flashed;
    }

    public Set<Pos> findNeighbours(Grid g, Pos p) {
        Set<Pos> nb = new HashSet<>();
        if (p.row > 0) {
            nb.add(new Pos(p.row - 1, p.col));
        }
        if (p.row < g.energyLevels.length - 1) {
            nb.add(new Pos(p.row + 1, p.col));
        }
        if (p.col > 0) {
            nb.add(new Pos(p.row, p.col - 1));
        }
        if (p.col < g.energyLevels[0].length - 1) {
            nb.add(new Pos(p.row, p.col + 1));
        }

        // upper left
        if (p.row > 0 && p.col > 0) {
            nb.add(new Pos(p.row - 1, p.col - 1));
        }
        // upper right
        if (p.row > 0 && p.col < g.energyLevels[0].length - 1) {
            nb.add(new Pos(p.row - 1, p.col + 1));
        }
        //lower left
        if (p.row < g.energyLevels.length - 1 && p.col > 0) {
            nb.add(new Pos(p.row + 1, p.col - 1));
        }
        //lower right
        if (p.row < g.energyLevels.length - 1 && p.col < g.energyLevels[0].length - 1) {
            nb.add(new Pos(p.row + 1, p.col + 1));
        }

        return nb;
    }

    public void increaseLevels(Grid g) {
        int[][] l = g.energyLevels;
        for (int row = 0; row < l.length; row++) {
            for (int col = 0; col < l[row].length; col++) {
                l[row][col]++;
            }
        }
    }

    public boolean isFlashable(int row, int col, Grid g) {
        return g.energyLevels[row][col] > 9 && !g.flashed[row][col];
    }

    public Pos findFlashable(Grid g) {
        int[][] l = g.energyLevels;
        for (int row = 0; row < l.length; row++) {
            for (int col = 0; col < l[row].length; col++) {
                if (isFlashable(row, col, g)) {
                    return new Pos(row, col);
                }
            }
        }
        return null;
    }

    public Grid parse(List<String> input) {
        int[][] levels = new int[input.size()][input.get(0).length()];
        int i = 0;
        for (String s : input) {
            int[] a = StreamSupport.stream(Splitter.fixedLength(1).split(s).spliterator(), false)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            levels[i] = a;
            i++;
        }

        return new Grid(levels);
    }

    public static void main(String[] args) throws IOException {
        Day11 d = new Day11();
        InputStream is = Day9.class.getClassLoader().getResourceAsStream("inputday11.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);
        System.out.println("SDf" + d.two(linesString));
    }
}

class Grid {
    int[][] energyLevels = new int[10][10];
    boolean[][] flashed = new boolean[10][10];

    public Grid(int[][] l) {
        this.energyLevels = l;
        flashed = new boolean[l.length][l[0].length];
    }

}

class Pos {
    public Pos() {

    }

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int row, col;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pos pos = (Pos) o;
        return row == pos.row && col == pos.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

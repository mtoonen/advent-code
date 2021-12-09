package nl.meine.adventofcode._2021;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Day9 {

    public int one(List<String> input) {
        Heightmap h = parseHeightMap(input);
        List<Integer> lps = findLowPoints(h);
        List<Integer> rls = findRiskLevels(lps);
        int sum = sumRiskLevels(rls);
        return sum;
    }

    public Heightmap parseHeightMap(List<String> input) {
        Heightmap result = new Heightmap();
        result.matrix = new int[input.size()][input.get(0).length()];
        int row = 0;
        for (String line : input) {
            List<String> tokens = Lists.newArrayList(Splitter.fixedLength(1).split(line));
            result.matrix[row] = tokens.stream().mapToInt(Integer::parseInt).toArray();
            row++;
        }
        return result;
    }

    public List<Integer> findLowPoints(Heightmap heightmap) {
        List<Integer> result = new ArrayList<>();

        for (int row = 0; row < heightmap.matrix.length; row++) {
            for (int col = 0; col < heightmap.matrix[row].length; col++) {
                if (isLowPoint(heightmap, row, col)) {
                    result.add(heightmap.matrix[row][col]);
                }
            }
        }

        return result;
    }

    public boolean isLowPoint(Heightmap h, int x, int y) {
        int val = h.matrix[x][y];
        List<Integer> neighbours = new ArrayList<>();
        // up
        if (y > 0) {
            neighbours.add(h.matrix[x][y - 1]);
        }
        //down
        if (h.matrix[x].length > y + 1) {
            neighbours.add(h.matrix[x][y + 1]);
        }
        //left
        if (x > 0) {
            neighbours.add(h.matrix[x - 1][y]);
        }
        //right
        if (h.matrix.length > x + 1) {
            neighbours.add(h.matrix[x + 1][y]);
        }

        return neighbours.stream().allMatch(neighbour -> neighbour > val);
    }

    public List<Integer> findRiskLevels(List<Integer> lowPoints) {
        return lowPoints.stream().map(x -> x + 1).collect(Collectors.toList());
    }

    public int sumRiskLevels(List<Integer> risklevels) {
        return risklevels.stream().mapToInt(Integer::intValue).sum();
    }

    // vind eerste 0
    // ga naar links tot 9: pos X
    // tel voor deze rij all 0-en, zet ze op 1, sla de meest rechter positie op: POS Y
    // check of er in de rij eronder een 0 zit, zo ja
    // begin bij POS X, Y-1 en ga naar links tot je een 9 vind
    // tel alle 0-en, totdat je X minimaal POSY.x is EN je huidige een 9

    public int two(List<String> input) {
        Heightmap h = parseHeightMap(input);
        h = resetNonNine(h);
        Coord first = findEmptySpot(h);

        List<Integer> sizes = new ArrayList<>();
        while (first != null) {
            int size = countBasin(first, h);
            sizes.add(size);
            first = findEmptySpot(h);
        }
        List<Integer> topThree = findThreeLargest(sizes);
        return multiplyEtries(topThree);
    }

    public int countBasin(Coord s, Heightmap h) {
        Set<Coord> todo = new HashSet<>();
        todo.add(s);
        Set<Coord> zeroCoords = traverse(todo, h);
        setToOne(zeroCoords, h);
        return zeroCoords.size();
    }

    public Set<Coord> traverse(Set<Coord> todos, Heightmap h) {
        // ga naar rechts tot 9, voeg alles toe
        // kijk voor elk item of er onder een 0 zit zo ja, voeg toe

        Set<Coord> temp = new HashSet<>();
        for (Coord c : todos) {
            temp.addAll(getNeighbours(h, c));
        }
        setToOne(temp, h);
        if(todos.addAll(temp)){

            traverse(todos, h);
        }
        return todos;
    }

    public void setToOne(Set<Coord>coords, Heightmap h){
        for (Coord c: coords) {
            h.matrix[c.y][c.x] = 1;
        }
    }

    public Set<Coord> getNeighbours(Heightmap h, Coord c) {
        Set<Coord> todos = new HashSet<>();
        if ((c.x + 1) < h.matrix[c.y].length && h.matrix[c.y][c.x + 1] == 0) {
            todos.add(new Coord(c.x + 1, c.y));
        }
        if (c.x != 0 && h.matrix[c.y][c.x - 1] == 0) {
            todos.add(new Coord(c.x - 1, c.y));
        }
        if ((c.y + 1) < h.matrix.length && h.matrix[c.y + 1][c.x] == 0) {
            todos.add(new Coord(c.x, c.y + 1));
        }
        if (c.y != 0 && h.matrix[c.y - 1][c.x] == 0) {
            todos.add(new Coord(c.x, c.y - 1));
        }
        return todos;
    }

    public boolean hasNeighbour(Coord s, Heightmap h) {
        return false;
    }


    public Heightmap resetNonNine(Heightmap h) {
        for (int row = 0; row < h.matrix.length; row++) {
            for (int col = 0; col < h.matrix[row].length; col++) {
                if (h.matrix[row][col] != 9) {
                    h.matrix[row][col] = 0;
                }
            }
        }
        return h;
    }

    public Coord findEmptySpot(Heightmap h) {
        for (int row = 0; row < h.matrix.length; row++) {
            Coord c = findEmptySpot(h, row);
            if (c != null) {
                return c;
            }
        }
        return null;
    }

    public Coord findEmptySpot(Heightmap h, int row) {
        for (int col = 0; col < h.matrix[row].length; col++) {
            if (h.matrix[row][col] == 0) {
                return new Coord(col, row);
            }
        }
        return null;
    }

    public List<Integer> findThreeLargest(List<Integer> sizes) {
        List<Integer> ints = sizes.stream().sorted().collect(Collectors.toList());
        Collections.reverse(ints);
        return ints.stream().limit(3).collect(Collectors.toList());
    }

    public int multiplyEtries(List<Integer> sizes) {
        int result = sizes.get(0);
        result *= sizes.get(1);
        result *= sizes.get(2);
        return result;
    }


    // maak alle niet-negens 0
    // begin op 0,0
    // vind eerste 0, POS X
    // ga naar rechts tot je een 9 tegenkomt
    // zet elke plek die je tegenkomst op 1, hoog basinteller op
    // ga POS X->y-1
    // ga naar links tot je een 9 tegenkomt: POS Y
    // ga naar rechts tot je een 9 tegenkomst
    // sla basinteller in een list op
    // vind de 3 grootste basintellers, multiply

    public static void main(String[] args) throws IOException {
        Day9 d = new Day9();

        InputStream is = Day9.class.getClassLoader().getResourceAsStream("inputday9.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);
        System.out.println("Numbers larger: " + d.two(linesString));
    }


}

class Heightmap {
    int[][] matrix;
}

class Coord {
    public Coord() {

    }

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x, y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return x == coord.x && y == coord.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

package nl.meine.adventofcode._2021;

import com.google.common.base.Splitter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Day15 {

    public long one(List<String> input) {
        // parse input
        Maze m = parseInput(input);

        return solve(m);
    }

    public long solve(Maze m){
        // create maze with added costs
        //begin bij 0,0
        // voor elke buur, zet op de huidige positie in mazeCounted de cost op current + neightbour
        // als de buur in mazecounted al een cost heeft, check of de nieuwe kleiner is, zo ja overschrijf
        countCosts(m);
        // start at lower back, follow path to start, using the smallest number of the neighbour
        List<Pos> path = traverseAtLowestCost(m);
        for (Pos p : path) {

            int cost = m.maze[p.row][p.col];
            System.out.println(p.row + "," + p.col + "->" + cost);

        }
        int total = 0;
        for (int i = 0; i < path.size() ; i++) { // do not count the last one, as it is te starting point
            Pos p = path.get(i);
            int cost = m.maze[p.row][p.col];
            total += cost;
        }
        return total;// m.mazeCounted[0][0] - m.maze[0][0];
    }

    public List<Pos> traverseAtLowestCost(Maze m) {
        List<Pos> path = new ArrayList<>();
        Set<Pos> travelled = new HashSet<>();
       // Pos cur = new Pos(m.maze.length - 1, m.maze[0].length - 1);
        Pos cur = new Pos(0, 0);
        path.add(cur);
        travelled.add(cur);
        while (cur != null) {
            Set<Pos> nbs = getNeighbours(m, cur);
            nbs.removeAll(travelled);
            cur = getLowestCost(nbs, m);
            if (cur != null && cur.row != m.maze.length - 1 && cur.col != m.maze[0].length - 1) {
                path.add(cur);
                travelled.add(cur);
            } else {
                cur = null;
            }

        }
        return path;
    }

    public Pos getLowestCost(Set<Pos> positions, Maze m) {
        return positions
                .stream()
                .min(Comparator.comparingInt(pos -> m.mazeCounted[pos.row][pos.col])).orElse(null);
    }

    public void countCosts(Maze m) {
        m.mazeCounted[m.maze.length - 1][m.maze[0].length - 1] = m.maze[m.maze.length - 1][m.maze[0].length - 1];
        for (int row = m.maze.length - 1; row >= 0; row--) {
            for (int col = m.maze[row].length - 1; col >= 0; col--) {
                Set<Pos> nbs = getNeighbours(m, new Pos(row, col));
                int curCost = m.mazeCounted[row][col];
                for (Pos nb : nbs) {
                    writeCost(m, nb, curCost);
                }
            }
        }
    }


    public void writeCost(Maze m, Pos p, int costOfDeparture) {
        int costAtArrival = m.maze[p.row][p.col];
        int currentCostAtArrival = m.mazeCounted[p.row][p.col];
        if (currentCostAtArrival == 0 || currentCostAtArrival > (costAtArrival + costOfDeparture)) {
            m.mazeCounted[p.row][p.col] = costAtArrival + costOfDeparture;
        }
    }


    public Maze parseInput(List<String> input) {
        int[][] maze = new int[0][0];

        maze = input.stream().map(s -> parseLine(s)).collect(Collectors.toList()).toArray(maze);
        //maze[0][0] = 0;
        return new Maze(maze);
    }

    public int[] parseLine(String line) {
        return StreamSupport.stream(Splitter.fixedLength(1).split(line).spliterator(), false)
                .mapToInt(Integer::parseInt)
                .toArray();
    }


    public Set<Pos> getNeighbours(Maze m, Pos c) {
        Set<Pos> neighbours = new HashSet<>();
        if ((c.col + 1) < m.maze[c.row].length) {
            neighbours.add(new Pos(c.row, c.col + 1));
        }
        if (c.col != 0) {
            neighbours.add(new Pos(c.row, c.col - 1));
        }
        if ((c.row + 1) < m.maze.length) {
            neighbours.add(new Pos(c.row + 1, c.col));
        }
        if (c.row != 0) {
            neighbours.add(new Pos(c.row - 1, c.col));
        }
        return neighbours;

    }

    public long two(List<String> input) {

        Maze m = parseInput(input);
        expandMaze(m);
        return solve(m);
    }

    public void expandMaze(Maze m){
        expandToSize(m, 5);
    }

    public void expandToSize(Maze m, int size) {
        Maze currentMaze = m;
        for (int row = 0; row < size-1; row++) {
            Maze down = copyMaze(currentMaze);
            increaseLevels(down);
            mergeToDown(down, m);
            currentMaze = down;
        }
        currentMaze = m;
        for (int col = 0; col < size-1; col++) {
            Maze left = copyMaze(currentMaze);
            increaseLevels(left);
            mergeToLeft(left, m);

            currentMaze = left;
        }
    }

    public Maze copyMaze(Maze m){
        return new Maze(Arrays.stream(m.maze).map(int[]::clone).toArray(int[][]::new));
    }

    public void increaseLevels(Maze m){
        for (int row = 0; row < m.maze.length; row++) {
            for (int col = 0; col < m.maze[row].length; col++) {
                int curValue = m.maze[row][col];
                if(curValue == 9){
                    m.maze[row][col] = 1;
                }else{
                    m.maze[row][col]++;
                }
            }
        }

    }

    public void mergeToLeft(Maze from, Maze to){
        int[][] newArray = new int[to.maze.length ][to.maze[0].length + from.maze[0].length];
        int idx = 0;
        for (int[]row: from.maze) {
            newArray[idx] = ArrayUtils.addAll(to.maze[idx], row);
            idx++;
        }
        to.update(newArray);
    }

    public void mergeToDown(Maze from, Maze to){
        int[][] newMaze = ArrayUtils.addAll(to.maze, from.maze);
        to.update(newMaze);
    }


    public static void main(String[] args) throws IOException {
        Day15 d = new Day15();

        InputStream is = Day14.class.getClassLoader().getResourceAsStream("inputday15.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);

        System.out.println("Numbers larger: " + d.two(linesString));

    }

}

class Maze {
    int[][] maze;
    int[][] mazeCounted;

    public Maze(int[][] maze) {
        update(maze);
    }

    public void update(int[][]newMaze){
        this.maze = newMaze;
        this.mazeCounted = new int[maze.length][maze[0].length];
        Arrays.stream(mazeCounted).forEach(g -> Arrays.fill(g, 0));

    }
}

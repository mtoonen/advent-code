package nl.meine.adventofcode._2021;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {

    public long one(List<String> input, String foldInstruction) {
        List<Pos> positions = parsePositions(input);
        String[][] grid = fillGrid(positions);
        Pos divider = parseInstruction(foldInstruction);
        String[][] folded = null;
        if(divider.row != 0){
            folded = foldUp(divider.row, grid);
        }else{
           folded = foldLeft(divider.col, grid);
        }


        return countDots(folded);
    }

    public long countDots(String[][] folded){
        return Arrays.asList(folded).stream()
                .flatMap(Arrays::stream)
                .filter(s -> s.equals("#")).count();
    }

    public Pos parseInstruction(String input){
        int index = input.indexOf("=");
        String xory = input.substring(index-1, index);
        int coord = Integer.parseInt(input.substring(index+1));
        return new Pos(xory.equals("y") ? coord : 0,xory.equals("x") ? coord : 0);
    }


    public String[][] fillGrid(List<Pos> pos){
        int maxX = pos.stream().max(Comparator.comparingInt(p -> p.row)).get().row+1;
        int maxY = pos.stream().max(Comparator.comparingInt(p -> p.col)).get().col+1;
        String [][] grid = new String[maxX][maxY];
        Arrays.stream(grid).forEach(g ->Arrays.fill(g, "."));
        pos.forEach(p->{
            grid[p.row][p.col] = "#";
        });
        return grid;
    }


    public String[][] foldUp(int y, String[][] grid){
        String[][] upper = Arrays.copyOfRange(grid, 0, y);
        String[][] lower = Arrays.copyOfRange(grid, y+1, grid.length);;
        ArrayUtils.reverse(upper);
        return merge(upper, lower);
    }

    public String[][] foldLeft(int x, String[][] grid){
        List<String[]> left = Arrays.stream(grid).map(k->Arrays.copyOfRange(k, 0, x)).collect(Collectors.toList());
        String[][] right = Arrays.stream(grid).map(k->Arrays.copyOfRange(k, x+1, k.length)).collect(Collectors.toList()).toArray(new String[0][0]);;

        left.stream().forEach(ArrayUtils::reverse);

        String[][] reversed= left.toArray(new String[0][0]);
        return merge(reversed, right);
    }

    public String [][] merge(String[][] left, String[][] right){
        for (int row = 0; row < right.length; row++) {
            for (int col = 0; col < right[0].length; col++) {
                if(right[row][col].equals("#")){
                    left[row][col] = "#";
                }
            }
        }
        return left;
    }


    public List<Pos> parsePositions(List<String> input){
        return input.stream()
                .map(s->mapStringToPos(s))
                .collect(Collectors.toList());
    }

    private Pos mapStringToPos(String input){
        String[] tokens = input.split(",");
        return new Pos(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[0]));
    }
    public int two(List<String> input) {
        List<String> instructions = new ArrayList<>();
        instructions.add("fold along x=655");
        instructions.add("fold along y=447");
        instructions.add("fold along x=327");
        instructions.add("fold along y=223");
        instructions.add("fold along x=163");
        instructions.add("fold along y=111");
        instructions.add("fold along x=81");
        instructions.add("fold along y=55");
        instructions.add("fold along x=40");
        instructions.add("fold along y=27");
        instructions.add("fold along y=13");
        instructions.add("fold along y=6");

        List<Pos> positions = parsePositions(input);
        String[][] grid = fillGrid(positions);
        for (String foldInstruction: instructions) {
            Pos divider = parseInstruction(foldInstruction);
            if(divider.row != 0){
                grid = foldUp(divider.row, grid);
            }else{
                grid = foldLeft(divider.col, grid);
            }
        }
        System.out.println(grid);

        for (String[] a: grid) {
            for (String s: a) {
                if(s.equals("#")){
                    System.out.print("#");
                }else{
                    System.out.print(" ");
                }
            }

            System.out.println("   |    ");
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        Day13 d = new Day13();

        InputStream is = Day13.class.getClassLoader().getResourceAsStream("inputday13.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);


        System.out.println("Numbers larger: " + d.two(linesString));

    }


}

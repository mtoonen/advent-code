package nl.meine.advent.dayone;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

    public int one(List<String> input, String numbersString) {
        List<Board> boards = parseBoards(input);
        List<Integer> numbers = parseNumbers(numbersString);
        for (Integer number : numbers) {
            for (Board b : boards) {
                b.call(number);
                if (b.checkBoard()) {
                    int row = b.rowCorrect();
                    int col = b.columnCorrect();
                    int sum = b.sumUnmarked();

                    return sum * number;
                }

            }

        }
        int numLarger = -1;
        return numLarger;
    }

    public int two(List<String> input, String inputNumbers) {
        List<Board> boards = parseBoards(input);
        List<Integer> numbers = parseNumbers(inputNumbers);
        int lastAnswer = 0;
        for (Integer number : numbers) {
            for (Board b : boards) {
                if(b.finished)
                    continue;
                b.call(number);
                if (b.checkBoard()) {
                    int row = b.rowCorrect();
                    int col = b.columnCorrect();
                    int sum = b.sumUnmarked();

                    lastAnswer= sum * number;
                    b.finished = true;
                    int a =0;
                }

            }

        }
        int numLarger = -1;
        return lastAnswer;
    }

    public List<Integer> parseNumbers(String s) {
        return Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public List<Board> parseBoards(List<String> boardsString) {
        List<Board> bs = new ArrayList<>();
        for (int i = 0; i < boardsString.size() - 4; i += 6) {
            List<String> lines = boardsString.subList(i , i + 5);
            bs.add(parseBoard(lines));
        }

        return bs;
    }

    public Board parseBoard(List<String> lines) {
        Integer[][] matrix = new Integer[5][5];
        matrix = lines.stream().map(s -> parseLine(s)).collect(Collectors.toList()).toArray(matrix);

        return new Board(matrix);
    }

    public Integer[] parseLine(String line) {
        String[] ar = line.split(" ");
        return Arrays.stream(ar).filter(x -> x.length() > 0).map(Integer::parseInt).collect(Collectors.toList()).toArray(new Integer[0]);
    }

    public static void main(String[] args) throws IOException {
        Day4 d = new Day4();

        InputStream is = Day4.class.getClassLoader().getResourceAsStream("inputday4.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);

        String numbersString = "91,17,64,45,8,13,47,19,52,68,63,76,82,44,28,56,37,2,78,48,32,58,72,53,9,85,77,89,36,22,49,86,51,99,6,92,80,87,7,25,31,66,84,4,98,67,46,61,59,79,0,3,38,27,23,95,20,35,14,30,26,33,42,93,12,57,11,54,50,75,90,41,88,96,40,81,24,94,18,39,70,34,21,55,5,29,71,83,1,60,74,69,10,62,43,73,97,65,15,16";
        System.out.println("Numbers larger: " + d.two(linesString, numbersString));

    }


}

class Board {
    boolean finished = false;
    boolean[][] called = new boolean[5][5];

    Integer[][] matrix;

    public Board(Integer[][] matrix) {
        this.matrix = matrix;
    }

    public void call(int number) {
        List<Integer> colsFound = new ArrayList<>();
        List<Integer> rowsFound = new ArrayList<>();

        for (int col = 0; col < matrix.length; col++) {
            for (int row = 0; row < matrix[col].length; row++) {
                if (matrix[col][row] == number) {
                    rowsFound.add(row);
                    colsFound.add(col);
                }
            }
        }

        for (int i = 0; i < colsFound.size(); i++) {
            int col = colsFound.get(i);
            int row = rowsFound.get(i);
            called[col][row] = true;
        }
    }


    Integer[][] getMatrix() {
        return matrix;
    }

    boolean checkBoard() {
        int rowCorrect = rowCorrect();
        int colCorrect = columnCorrect();
        return rowCorrect != -1 || colCorrect != -1;
    }

    int rowCorrect() {
        for (int row = 0; row < 5; row++) {
            boolean correct = true;
            for (int col = 0; col < 5; col++) {
                if (!called[row][col]) {
                    correct = false;
                    break;
                }
            }
            if (correct) {
                return row;
            }
        }
        return -1;
    }

    int columnCorrect() {
        for (int row = 0; row < 5; row++) {
            boolean correct = true;
            for (int col = 0; col < 5; col++) {
                if (!called[col][row]) {
                    correct = false;
                    break;
                }
            }
            if (correct) {
                return row;
            }
        }
        return -1;
    }

    int sumRow(int row) {
        return Arrays.stream(matrix[row]).reduce(0, Integer::sum);
    }

    int sumCol(int col) {
        int sum = 0;

        for (int row = 0; row < matrix[col].length; row++) {
            sum += matrix[row][col];

        }
        return sum;
    }

    int sumUnmarked (){
        int sum = 0;
        for (int row = 0; row < called.length; row++) {
            for (int col = 0; col < called[row].length; col++) {
                if (!called[col][row]) {
                    sum += matrix[col][row];
                }
            }
        }
        return sum;
    }
}

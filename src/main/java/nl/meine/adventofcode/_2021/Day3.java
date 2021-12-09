package nl.meine.adventofcode._2021;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Day3 {


    public int two(List<String> input){
        List<List<Integer>> oxyList = parseInput(input);
        List<List<Integer>> coList = parseInput(input);

        int oxy = getValue(oxyList, true);
        int co2 = getValue(coList, false);
        return oxy * co2;
    }

    public int getValue(List<List<Integer>> input,  boolean most){
        List<List<Integer>> parsed = new ArrayList<>(input);
        int index = 0;
        do {
            parsed = filter(parsed, index, most);
            index++;
            if(parsed.get(0).size() == 1){
                break;
            }
        } while (true);

        String oxy = "";
        for (List<Integer>column: parsed) {
            oxy += column.get(0);
        }
        return Integer.parseInt(oxy, 2);
    }


    public List<List<Integer>> filter(List<List<Integer>> input, int index, boolean most){
        // determine most common bit
        int bit = determineBit(input, index, most);
        List<Integer> column = getColumn(input, index);

        List<Integer> indicesToBeRemoved = new ArrayList<>();
        for (int i = 0; i < column.size(); i++) {
            int curBit = column.get(i);
            if( curBit!= bit){
                indicesToBeRemoved.add(i);
            }
        }

        for (List<Integer> col: input) {
            for (int i = (indicesToBeRemoved.size() -1); i >= 0 ; i--) {
                int remove = indicesToBeRemoved.get(i);
                col.remove(remove);
            }
        }

        return input;
    }

    public int determineBit(List<List<Integer>> input, int index, boolean most){
        List<Integer> column = getColumn(input, index);

        long mostZero = getNumberOfBit(0, column);
        long mostOne = getNumberOfBit(1, column);
        int selectedBit = -1;
        if(most){
            selectedBit = mostZero > mostOne ? 0 : 1;
        }else{
            selectedBit = mostOne < mostZero ? 1 : 0;
        }

        return selectedBit;
    }



    public int one(List<String> input){
        List<List<Integer>> parsed = parseInput(input);
        String gamma = "", epsilon = "";
        for (List<Integer> column: parsed) {
            long mostZero = getNumberOfBit(0, column);
            long mostOne = getNumberOfBit(1, column);
            if(mostZero > mostOne ){
                gamma += "0";
                epsilon += "1";
            }else{
                gamma += "1";
                epsilon += "0";
            }
        }
        int decimalGamma=Integer.parseInt(gamma,2);
        int decimalEpsilon=Integer.parseInt(epsilon,2);
        return decimalEpsilon * decimalGamma;
    }

    public long getNumberOfBit(int bit, List<Integer> input){
        return input.stream().filter(bitValue ->bitValue == bit ).count();
    }

    public List<List<Integer>> parseInput(List<String> input){
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < input.get(0).length(); i++) {
            output.add(new ArrayList<>());
        }
        for (int i = 0; i < input.size(); i++) {
            String row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                String c = row.substring(j, j+1);
                output.get(j).add(Integer.parseInt(c));
            }
        }
        return output;
    }

    public List<Integer> getColumn(List<List<Integer>> input, int index){
        return input.get(index);
    }

    public static void main(String[] args) throws IOException {
        Day3 d = new Day3();

        InputStream is = Day1.class.getClassLoader().getResourceAsStream("inputdaythree.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);
        System.out.println("Output:" + d.two(linesString));
    }
}

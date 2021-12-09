package nl.meine.adventofcode._2021;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Day {

    public int one(List<Integer> input) {
        int numLarger = -1;
        return numLarger;
    }

    public int two(List<Integer> input) {
        int numLarger = -1;

        return numLarger;
    }

    public static void main(String[] args) throws IOException {
        Day d = new Day();

        InputStream is = Day.class.getClassLoader().getResourceAsStream("inputdayone.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);

        List<Integer> lines = linesString.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        System.out.println("Numbers larger: " + d.two(lines));

    }


}

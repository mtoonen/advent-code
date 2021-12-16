package nl.meine.adventofcode._2021;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day6 {

    List<Integer> days = List.of(1,2,3,4,5,6,7,8);
    public Long one(String input) {
        int numLarger = -1;
        // verdeel in een map met als key het aantal dagen over, en als value het aantal laternfish
        // per dag
            // verlaag van alle keys (behalve 0 en 7) de key met 1
            // voor 0: zet op 6 en voeg een 8 toe
            // merge de aantallen van 7 naar 6
        Map<Integer, Long> daysToCount = getMap(input);
        for (int i = 0; i < 80; i++) {
            step(daysToCount);
        }
        return daysToCount.values().stream().reduce(0L, Long::sum);
    }

    public Map<Integer, Long> getMap(String input){
        Map<Integer, Long> m = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toMap(s->s, s->1L, Long::sum));
        days.stream()
                .forEach(d->m.merge(d, 0L, Long::sum));
        m.merge(0, 0L, Long::sum);
        return m;
    }
    public Map<Integer, Long> step(Map<Integer, Long> daysToCount){
        // verlaag van alle keys (behalve 0 en 7) de key met 1
        // voor 0: zet op 6 en voeg een 8 toe
        // merge de aantallen van 7 naar 6
        Long zeroDay = daysToCount.get(0);

        for (Integer day:days) {
            daysToCount.put(day - 1, daysToCount.get(day));
        }

        daysToCount.merge(6, zeroDay, Long::sum);
        daysToCount.put(8, zeroDay);

        return daysToCount;
    }

    public Long two(String input) {
        // verdeel in een map met als key het aantal dagen over, en als value het aantal laternfish
        // per dag
        // verlaag van alle keys (behalve 0 en 7) de key met 1
        // voor 0: zet op 6 en voeg een 8 toe
        // merge de aantallen van 7 naar 6
        Map<Integer, Long> daysToCount = getMap(input);
        for (int i = 0; i < 256; i++) {
            step(daysToCount);
        }
        return daysToCount.values().stream().reduce(0L, Long::sum);
    }

    public static void main(String[] args) throws IOException {
        Day6 d = new Day6();

        InputStream is = Day6.class.getClassLoader().getResourceAsStream("inputday6.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);


        System.out.println("Numbers larger: " + d.two(linesString.get(0)));

    }


}

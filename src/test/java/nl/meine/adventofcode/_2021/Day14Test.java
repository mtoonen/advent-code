package nl.meine.adventofcode._2021;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    Day14 instance;
    List<String> input;

    @BeforeEach
    void setUp() throws IOException {
        instance = new Day14();
        InputStream is = Day8.class.getClassLoader().getResourceAsStream("inputday14.txt");
        input = IOUtils.readLines(is, StandardCharsets.UTF_8);

    }

    @Test
    void one() {
        String template = "NNCB";
        Date d = new Date();
        assertEquals(1588, instance.one(input, template));
        Date end = new Date();
        System.out.println("si" + (end.getTime() - d.getTime()));
    }



    @Test
    void two() {
        String template = "NNCB";
        assertEquals(2188189693529L, instance.two(input, template));
    }

    @Test
    void step() {
        String template = "NNCB";
        List<Insertion> ins = instance.parseInsertions(input);
        Map<String, Insertion> lookup = ins.stream()
                .collect(Collectors.toMap(i -> i.pair, i -> i));
        Map<String, String> templateLookup = new HashMap<>();
        assertEquals("NCNBCHB", instance.step(lookup, template,templateLookup));
        assertEquals("NBCCNBBBCBHCB", instance.step(lookup, "NCNBCHB",templateLookup));
        assertEquals("NBBBCNCCNBBNBNBBCHBHHBCHB", instance.step(lookup, "NBCCNBBBCBHCB",templateLookup));
        assertEquals("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB", instance.step(lookup, "NBBBCNCCNBBNBNBBCHBHHBCHB",templateLookup));
    }

    @Test
    void getPairs() {
    }

    @Test
    void getInsertion() {
    }

    @Test
    void parseInsertions() {
    }

    @Test
    void main() {
    }
}

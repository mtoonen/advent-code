package nl.meine.adventofcode._2021;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day14 {

    public long one(List<String> input, String template) {
        List<Insertion> ins = parseInsertions(input);
        Map<String, Insertion> lookup = ins.stream()
                .collect(Collectors.toMap(i -> i.pair, i -> i));
        for (int i = 0; i < 10; i++) {
            template = stepSplit(lookup, template, new HashMap<>());
        }


        Map<Integer, String> oc = countInstances(template);
        Integer max = oc.keySet().stream().max(Integer::compareTo).get();
        Integer min = oc.keySet().stream().min(Integer::compareTo).get();

        return max - min;
    }


    public Long two(List<String> inp, String template) {
        // split start in paartjes en stop ze in een map met per paartje een count van 1

        String start = inp.get(0);
        Map<String, String> bindings = new HashMap<>();
        inp.subList(2, inp.size()).stream().map(s -> s.split(" -> ")).forEach(p -> bindings.put(p[0], p[1]));

        Map<String, Long> countsPerPair = new HashMap<>();
        for (int i = 0; i < start.length() - 1; i++) {
            countsPerPair.merge("" + start.charAt(i) + start.charAt(i + 1), 1L, Long::sum);
        }

        // expand elk paartje in 2 paartjes en voeg samen met de tellingmap
        for (int i = 0; i < 40; i++) {
            Map<String, Long> temp = new HashMap<>();
            for (String pair : countsPerPair.keySet()) {
                String left = pair.charAt(0) + bindings.get(pair);
                String right = bindings.get(pair) + pair.charAt(1);
                temp.merge(left, countsPerPair.get(pair), Long::sum);
                temp.merge(right, countsPerPair.get(pair), Long::sum);
            }
            countsPerPair = temp;
        }
        Long max = -1L, min = -1L;
        return max - min;
    }

    public Map<Integer, String> countInstances(String s) {
        List<String> dif = s.chars()
                .distinct()
                .mapToObj(Character::toString)
                .collect(Collectors.toList());
        Map<Integer, String> occurences = new HashMap<>();
        for (String c : dif) {

            occurences.put(StringUtils.countMatches(s, c), c);
        }
        return occurences;
    }


    public String stepSplit(Map<String, Insertion> insertionLookup, String template, Map<String, String> templateLookup) {
        List<String> results = new ArrayList<>();
        for (String part : Splitter.fixedLength(10).split(template)) {
            if (templateLookup.containsKey(part)) {
                results.add(templateLookup.get(part));
            } else {
                String result = step(insertionLookup, part, templateLookup);
                results.add(result);
                templateLookup.put(part, result);
            }
        }
        String total = null;
        for (String r : results) {
            if (total != null) {
                total = merge(total, r, insertionLookup);
            } else {
                total = r;
            }

        }
        return total;
    }

    public String step(Map<String, Insertion> insertionLookup, String template, Map<String, String> templateLookup) {
        if (templateLookup.containsKey(template)) {
            return templateLookup.get(template);
        }

        String[] pairs = getPairs(template);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < template.length(); i++) {
            sb.append(template.charAt(i));
            if (i <= pairs.length - 1) {
                String pair = pairs[i];
                Insertion insert = insertionLookup.get(pair);

                sb.append(insert.insertionString);
            }
        }
        String s = sb.toString();
        templateLookup.put(template, s);
        return sb.toString();
    }

    public String merge(String left, String right, Map<String, Insertion> insertionLookup) {
        String p1 = left.substring(left.length() - 1);
        String p2 = right.substring(0, 1);
        String pair = p1 + p2;
        String insertion = insertionLookup.get(pair).insertionString;
        return left + insertion + right;
    }

    public String[] getPairs(String template) {
        String[] pairs = new String[template.length() - 1];
        for (int i = 0; i < template.length() - 1; i++) {
            String pair = template.substring(i, i + 2);
            pairs[i] = pair;

        }
        return pairs;
    }

    public String getInsertion(String pair, List<Insertion> insertions) {
        return insertions.stream().filter(i -> i.pair.equals(pair)).findFirst().get().insertionString;
    }

    public List<Insertion> parseInsertions(List<String> input) {
        return input.stream()
                .map(s -> new Insertion(s)).collect(Collectors.toList());

    }


    public static void madin(String[] args) throws IOException {
        Day14 d = new Day14();

        InputStream is = Day14.class.getClassLoader().getResourceAsStream("inputday14.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);


        String template = "CNBPHFBOPCSPKOFNHVKV";
        System.out.println("Numbers larger: " + d.two(linesString, template));

    }

    public static void main(String[] args) throws IOException {
        InputStream is = Day14.class.getClassLoader().getResourceAsStream("inputday14.txt");
        List<String> inp = IOUtils.readLines(is, StandardCharsets.UTF_8);
        //var inp = Files.lines(Paths.get("inputday14.txt")).collect(Collectors.toList());
        String start = inp.get(0);
        Map<String, String> bindings = new HashMap<>();
        inp.subList(2, inp.size()).stream().map(s -> s.split(" -> ")).forEach(p -> bindings.put(p[0], p[1]));
        Map<String, Long> pairCounts = new HashMap<>();
        for (int i = 0; i < start.length() - 1; i++) {
            pairCounts.merge("" + start.charAt(i) + start.charAt(i + 1), 1L, Long::sum);
        }
        for (int i = 0; i < 40; i++) {
            Map<String, Long> temp = new HashMap<>();
            for (String pair : pairCounts.keySet()) {
                Long l =  pairCounts.get(pair);
                temp.merge(pair.charAt(0) + bindings.get(pair), pairCounts.get(pair), Long::sum);
                temp.merge(bindings.get(pair) + pair.charAt(1), pairCounts.get(pair), Long::sum);
            }
            pairCounts = temp;
        }
        Map<Character, Long> temp = new HashMap<>();
        for (Map.Entry<String, Long> entry : pairCounts.entrySet()) {
            temp.merge(entry.getKey().charAt(0), entry.getValue(), Long::sum);
        }
        temp.merge(start.charAt(start.length() - 1), 1L, Long::sum);
        System.out.println(temp.values().stream().max(Long::compareTo).get() - temp.values().stream().min(Long::compareTo).get());
    }
}

class Insertion {
    String pair;
    String insertionString;


    public Insertion(String total) {
        int index = total.indexOf("->");
        this.pair = total.substring(0, index - 1);
        this.insertionString = total.substring(index + 3);
    }
}

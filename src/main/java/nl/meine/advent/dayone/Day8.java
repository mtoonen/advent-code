package nl.meine.advent.dayone;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 {

    public int one(List<String> input) {
        List<Signal> signals = parseSignals(input);
        List<Integer> known = List.of(2, 3, 4, 7);
        int found = 0;
        for (Signal s : signals) {
            long pietje = countOccurences(known, s.output);
            found += pietje;
        }
        return found;
    }

    public long countOccurences(List<Integer> knownItems, List<String> output) {
        return output.stream()
                .filter(s1 ->knownItems.contains(s1.length()))
                .count();

    }

    public List<Signal> parseSignals(List<String> input) {
        List<Signal> output = new ArrayList<>();
        for (String s : input) {
            output.add(parseSignal(s));
        }
        return output;
    }

    public Signal parseSignal(String line) {
        int index = line.indexOf("|");
        String signals = line.substring(0, index).trim();
        String output = line.substring(index + 1).trim();
        return new Signal(List.of(signals.split(" ")), List.of(output.split(" ")));
    }

    public int two(List<String> input) {
        List<Signal> signals = parseSignals(input);
        int sum = 0;
        for (Signal s: signals) {
            Map<Integer, String> results = new HashMap<>();
            compute1478(results, s);
            compute3(results, s);
            compute6(results, s);
            compute5(results, s);
            compute9(results, s);
            deduce0(results, s);
            deduce2(results, s);
            Map<String, Integer> inverse = inverse(results);
            String output = determineOutput(inverse, s.output);
            sum += Integer.parseInt(output);
        }
        // compute 1, 4, 7, 8

        // compute 3

        // compute 6

        // compute 5

        // deduce 0

        // deduce 2

        // calculate output numbers

        // sum output numbers

        return sum;
    }

    public String determineOutput(Map<String, Integer> numbers, List<String> input){
        String result = "";
        for (String s:input) {
            s = sort(s.trim());
            result += numbers.get(s);

        }
        return result;
    }



    public<V,K> Map<K,V> inverse(Map<V,K> input){
        Map<K, V> myNewHashMap = new HashMap<>();
        for(Map.Entry<V, K> entry : input.entrySet()){
            myNewHashMap.put(entry.getValue(), entry.getKey());
        }
        return myNewHashMap;
    }

    public void compute1478(Map<Integer, String> numbers, Signal input){
        for (String s:input.signal) {
            int length = s.length();
            switch(length){
                case 2:
                    numbers.put(1, sort(s));
                    break;
                case 3:
                    numbers.put(7, sort(s));
                    break;
                case 4:
                    numbers.put(4, sort(s));
                    break;
                case 7:
                    numbers.put(8, sort(s));
                    break;

            }
        }

    }

    public void compute3(Map<Integer, String> numbers, Signal input){
        List<String> signalsLength5 = input.signal.stream().filter(s -> s.length() == 5).collect(Collectors.toList());
        String one =numbers.get(1);
        List<Character> oneElements = one.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        for (String s:signalsLength5) {
            List<Character> signalElements = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            boolean found = true;
            for (Character c: oneElements) {
                if(!signalElements.contains(c)){
                    found = false;
                }
            }
            if(found){
                numbers.put(3, sort(s));
                break;
            }

        }
    }

    public void compute6(Map<Integer, String> numbers, Signal input){
        List<String> signalsLength6 = input.signal.stream().filter(s -> s.length() == 6).collect(Collectors.toList());
        String one =numbers.get(1);
        List<Character> oneElements = one.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        for (String s:signalsLength6) {
            List<Character> signalElements = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            boolean found = false;
            for (Character c: oneElements) {
                if(!signalElements.contains(c)){
                    found = true;
                }
            }
            if(found){
                numbers.put(6, sort(s));
                break;
            }

        }
    }

    public void compute5(Map<Integer, String> numbers, Signal input){
        String three = numbers.get(3);
        String four = numbers.get(4);
        String eight = numbers.get(8);
        String six = numbers.get(6);


        List<Character> threeElements = toList(three);
        List<Character> fourElements = toList(four);
        List<Character> sixElements = toList(six);
        List<Character> eightElements = toList(eight);

        eightElements.removeAll(threeElements);
        eightElements.removeAll(fourElements);


        sixElements.removeAll(eightElements);
        String five = sixElements.stream().map(String::valueOf).collect(Collectors.joining());

        numbers.put(5, sort(five));


    }

    public void compute9(Map<Integer, String> numbers, Signal input){
        String three = numbers.get(3);
        String four = numbers.get(4);


        List<Character> threeElements = toList(three);
        List<Character> fourElements = toList(four);

        Set<Character> nineElements = new HashSet<>();
        nineElements.addAll(threeElements);
        nineElements.addAll(fourElements);

        String nine = nineElements.stream().map(String::valueOf).collect(Collectors.joining());

        numbers.put(9, sort(nine));
    }

    public boolean compare(String one, String two){
        return sort(one).equals(sort(two));
    }

    public static String sort(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }

    public List<Character> toList(String input){
        return input.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

    }

    // only remaining 6 length
    public void deduce0(Map<Integer, String> numbers, Signal input) {
        String d = input.signal.stream().filter(s -> s.length() == 6).filter(s -> {
                    String sorted = sort(s);
                    return !sorted.equals(numbers.get(6)) && !sorted.equals(numbers.get(9));
                }
        ).findFirst().get();
        numbers.put(0, sort(d));
    }

    // only remaining 5 length
    public void deduce2(Map<Integer, String> numbers, Signal input){
        String d = input.signal.stream().filter(s -> s.length() == 5).filter(s -> {
                    String sorted = sort(s);
                    return !sorted.equals(numbers.get(3)) && !sorted.equals(numbers.get(5));
                }
        ).findFirst().get();
        numbers.put(2, sort(d));

    }




    public static void main(String[] args) throws IOException {
        Day8 d = new Day8();

        InputStream is = Day8.class.getClassLoader().getResourceAsStream("inputday8.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);


        System.out.println("Numbers larger: " + d.two(linesString));

    }

}

class Signal {
    List<String> signal;
    List<String> output;

    public Signal(List<String> signal, List<String> output) {
        this.signal = signal;
        this.output = output;
    }
}

package nl.meine.adventofcode._2021;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Day10 {
    Map<Character, Character> closeToOpen = new HashMap<>();
    Map<Character, Character> openToClose = new HashMap<>();
    Map<Character, Integer> scoresOne = new HashMap<>();
    Map<Character, Integer> scoresTwo = new HashMap<>();

    List<Character> openels = List.of('[', '{', '(', '<');

    public Day10() {
        closeToOpen.put(')', '(');
        closeToOpen.put(']', '[');
        closeToOpen.put('}', '{');
        closeToOpen.put('>', '<');

        openToClose = closeToOpen.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        scoresOne.put(')', 3);
        scoresOne.put(']', 57);
        scoresOne.put('}', 1197);
        scoresOne.put('>', 25137);

        scoresTwo.put(')', 1);
        scoresTwo.put(']', 2);
        scoresTwo.put('}', 3);
        scoresTwo.put('>', 4);

    }

    public int one(List<String> input) {
        // check of string correct is
        // check of input incompleet is: mist een sluithaakje
        // check of imput incorrect is: er is een sluitelement die niet hetzelfde is als het openelement

        // begin links
        // als open elmeent
        // voeg toe aan lijst
        // als sluit element
        // is het hetzelfde als laatste uit lijst
        // ja, verwijder openelement uit lijst
        // nee: incorrect
        // zijn er nog elementen in invoer
        // ja: begin opnieuw 1 stap verder
        // nee: zijn er nog elementen in lijst
        // ja: incompleet
        // nee: correct
        int score = 0;
        for (String line : input) {
            ParseStatus s = parseLine(line);
            if (s.status == Status.INCORRECT) {
                score += scoresOne.get(s.c.get(0));
            }

        }
        return score;
    }

    public ParseStatus parseLine(String input) {
        List<Character> currentOpenChars = new ArrayList<>();
        for (Character c : input.toCharArray()) {
            if (openels.contains(c)) {
                currentOpenChars.add(c);
            } else {
                Character correspondingOpen = closeToOpen.get(c);
                int lastIndex = currentOpenChars.size() - 1;
                Character lastOpen = currentOpenChars.get(lastIndex);
                if (correspondingOpen.equals(lastOpen)) {
                    currentOpenChars.remove(lastIndex);
                } else {
                    return new ParseStatus(Status.INCORRECT, List.of(c));
                }
            }
        }

        if (currentOpenChars.isEmpty()) {
            return new ParseStatus(Status.COMPLETE, null);
        } else {
            return new ParseStatus(Status.INCOMPLETE, currentOpenChars);
        }
    }


    public boolean isComplete(String s) {
        return parseLine(s).status == Status.COMPLETE;
    }

    public Character getCorruptedCloser(String input) {
        return parseLine(input).c.get(0);
    }

    public Long two(List<String> input) {
        List<String> incompletes = getIncompleteLines(input);
        List<Long> scores = new ArrayList<>();
        for (String inco: incompletes) {
            String completionString = computCompletionString(inco);
            Long score = scoreString(completionString);
            scores.add(score);
        }
        return getMiddleScore(scores);
    }

    public List<String> getIncompleteLines(List<String> input) {
        List<String> incompletes = new ArrayList<>();
        for (String l : input) {
            ParseStatus status = parseLine(l);
            if (status.status == Status.INCOMPLETE) {
                incompletes.add(l);
            }

        }
        return incompletes;
    }

    public String computCompletionString(String input) {
        ParseStatus s = parseLine(input);
        List<Character> remainingOpeners = s.c;
        String completionString = Lists.reverse(remainingOpeners).stream().map(open -> openToClose.get(open)).map(String::valueOf).collect(Collectors.joining());
        return completionString;
    }

    public Long scoreString(String input) {
        Long score = 0L;
        for (Character c: input.toCharArray()) {
            score *= 5;
            score += scoresTwo.get(c);
        }
        return score;
    }

    public Long getMiddleScore(List<Long> scores) {
        Collections.sort(scores);
        int middle = scores.size() /2 ;
        return scores.get(middle);
    }

    public static void main(String[] args) throws IOException {
        Day10 d = new Day10();

        InputStream is = Day9.class.getClassLoader().getResourceAsStream("inputday10.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);
        System.out.println("Numbers larger: " + d.two(linesString));
    }

}

class ParseStatus {
    Status status;
    List<Character> c;

    public ParseStatus(Status s, List<Character> c) {
        this.status = s;
        this.c = c;
    }
}

enum Status {
    COMPLETE,
    INCOMPLETE,
    INCORRECT;
}

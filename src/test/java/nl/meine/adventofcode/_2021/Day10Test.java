package nl.meine.adventofcode._2021;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class Day10Test {

    Day10 instance;
    List<String> input = null;
    @BeforeEach
    void init() throws IOException {
        instance = new Day10();
        InputStream is = Day8.class.getClassLoader().getResourceAsStream("inputday10.txt");
        input = IOUtils.readLines(is, StandardCharsets.UTF_8);
    }
    
    @Test
    void testIncomplete(){
        String input = "[({(<(())[]>[[{[]{<()<>>";
        assertFalse(instance.isComplete(input));
    }

    @Test
    void testCheckCorrupted(){
        String input = "{([(<{}[<>[]}>{[]{[(<()>";
        Character output = instance.getCorruptedCloser(input);
        assertEquals(Character.valueOf('}'), output);
    }

    @Test
    void testOne(){
        assertEquals(26397, instance.one(input));
    }

    @Test
    void two() {
        assertEquals(Long.valueOf(288957), instance.two(input));
    }

    @Test
    void getIncompleteLines() {
        List<String> incomplete = instance.getIncompleteLines(input);
        assertEquals(5, incomplete.size());
        assertEquals("[({(<(())[]>[[{[]{<()<>>", incomplete.get(0));
        assertEquals("[(()[<>])]({[<{<<[]>>(", incomplete.get(1));
        assertEquals("(((({<>}<{<{<>}{[]{[]{}", incomplete.get(2));
        assertEquals("{<[[]]>}<{[{[{[]{()[[[]", incomplete.get(3));
        assertEquals("<{([{{}}[<[[[<>{}]]]>[]]", incomplete.get(4));
    }

    @Test
    void computCompletionString() {
        assertEquals("}}]])})]",instance.computCompletionString("[({(<(())[]>[[{[]{<()<>>"));
        assertEquals(")}>]})",instance.computCompletionString("[(()[<>])]({[<{<<[]>>("));
        assertEquals("}}>}>))))",instance.computCompletionString("(((({<>}<{<{<>}{[]{[]{}"));
        assertEquals("]]}}]}]}>",instance.computCompletionString("{<[[]]>}<{[{[{[]{()[[[]"));
        assertEquals("])}>",instance.computCompletionString("<{([{{}}[<[[[<>{}]]]>[]]"));
    }

    @Test
    void scoreString() {
        assertEquals(Long.valueOf(288957), instance.scoreString("}}]])})]"));
        assertEquals(Long.valueOf(5566), instance.scoreString(")}>]})"));
        assertEquals(Long.valueOf(1480781), instance.scoreString("}}>}>))))"));
        assertEquals(Long.valueOf(995444), instance.scoreString("]]}}]}]}>"));
        assertEquals(Long.valueOf(294), instance.scoreString("])}>"));
    }

    @Test
    void getMiddleScore() {
        List<Long> scores = Lists.newArrayList(List.of(1L,2L,5L,3L,8L));
        assertEquals(Long.valueOf(3), instance.getMiddleScore(scores));
    }
    @Test
    void getMiddleScore2() {
        List<Long> scores = Lists.newArrayList(List.of(288957L,5566L,1480781L,995444L,294L));
        assertEquals(Long.valueOf(288957), instance.getMiddleScore(scores));
    }

    @Test
    void twoReals() throws IOException {

        InputStream is = Day8.class.getClassLoader().getResourceAsStream("inputday10_realz.txt");
        List<String> s = IOUtils.readLines(is, StandardCharsets.UTF_8);
        Long score = instance.two(s);
        assertEquals(Long.valueOf(3490802734L),score);
    }
}

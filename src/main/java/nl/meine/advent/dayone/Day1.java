package nl.meine.advent.dayone;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day1 {

    public int one(List<Integer> input) {
        int numLarger = 0;
        int curNumber = Integer.MAX_VALUE;
        for (Integer cur : input) {
            if (cur > curNumber) {
                numLarger++;
            }
            curNumber = cur;
        }
        System.out.println("Numbers larger: " + numLarger);
        return numLarger;
    }

    public int two(List<Integer> input) {
        Map<Integer, List<Integer>> groups = groupList(input);
        int numLarger = 0;
        int curNumber = Integer.MAX_VALUE;

        for (Integer groupId : groups.keySet()) {
            List<Integer> measurements = groups.get(groupId);
            int cur = measurements.stream().collect(Collectors.summingInt(Integer::intValue));
            if (cur > curNumber) {
                numLarger++;
            }
            curNumber = cur;
        }

        return numLarger;
    }

    public Map<Integer, List<Integer>> groupList(List<Integer> input) {
        Map<Integer, List<Integer>> groups = new HashMap<>();
        int group = 0;
        int groupSize = 3;
        for (int i = 0; i < input.size(); i++) {

            if (groups.containsKey(group) && groups.get(group).size() == groupSize) {
                group += 3;
            }

            for (int groupOffset = 0; groupOffset < groupSize; groupOffset++) {
                int groupId = group + groupOffset;
                if (input.size() > i + groupOffset + (groupSize - 1) && !groups.containsKey(groupId)) {
                    groups.put(groupId, new ArrayList<>());
                }

                if (groups.containsKey(groupId)) {
                    groups.get(groupId).add(input.get(i + groupOffset));
                }
            }
        }
        return groups;
    }

    public static void main(String[] args) throws IOException {
        Day1 d = new Day1();

        InputStream is = Day1.class.getClassLoader().getResourceAsStream("inputdayone.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);

        List<Integer> lines = linesString.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        System.out.println("Numbers larger: " + d.two(lines));

    }


}

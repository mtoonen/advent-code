package nl.meine.advent.dayone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day1Test {

    Day1 instance;
    List<Integer> input = new ArrayList<>();

    @BeforeEach
    void setUp() {
        instance = new Day1();
        input.add(199);
        input.add(200);
        input.add(208);
        input.add(210);
        input.add(200);
        input.add(207);
        input.add(240);
        input.add(269);
        input.add(260);
        input.add(263);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void one() {
        assertEquals(7, instance.one(input));
    }

    @Nested
    class groupList{

        @Test
        void two() {
            assertEquals(5, instance.two(input));
        }


        @Test
        void separateIntoGroups() {
            Map<Integer, List<Integer>> groups = instance.groupList(input);
            assertEquals(8, groups.keySet().size());
        }

        @Test
        void fillGroups(){
            Map<Integer, List<Integer>> groups = instance.groupList(input);
            for (Integer key : groups.keySet()){
                assertEquals(3, groups.get(key).size());
            }
        }

        @Test
        void checkGroupsContents(){
            Map<Integer, List<Integer>> groups = instance.groupList(input);
            assertTrue(groups.get(0).contains(199));
            assertTrue(groups.get(0).contains(200));
            assertTrue(groups.get(0).contains(208));

            assertTrue(groups.get(1).contains(200));
            assertTrue(groups.get(1).contains(208));
            assertTrue(groups.get(1).contains(210));

            assertTrue(groups.get(2).contains(208));
            assertTrue(groups.get(2).contains(210));
            assertTrue(groups.get(2).contains(200));

            assertTrue(groups.get(3).contains(210));
            assertTrue(groups.get(3).contains(200));
            assertTrue(groups.get(3).contains(207));

            assertTrue(groups.get(4).contains(200));
            assertTrue(groups.get(4).contains(207));
            assertTrue(groups.get(4).contains(240));


            assertTrue(groups.get(5).contains(207));
            assertTrue(groups.get(5).contains(240));
            assertTrue(groups.get(5).contains(269));

            assertTrue(groups.get(6).contains(240));
            assertTrue(groups.get(6).contains(269));
            assertTrue(groups.get(6).contains(260));


            assertTrue(groups.get(7).contains(269));
            assertTrue(groups.get(7).contains(260));
            assertTrue(groups.get(7).contains(263));
        }

    }

    @Test()
    void main() {
    }
}

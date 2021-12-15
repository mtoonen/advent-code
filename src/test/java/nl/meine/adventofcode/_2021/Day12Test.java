package nl.meine.adventofcode._2021;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    Day12 instance;
    List<String> largeExample;
    List<String> smallExample = new ArrayList<>();

    @BeforeEach
    void setUp() throws IOException {
        instance = new Day12();
        InputStream is = Day8.class.getClassLoader().getResourceAsStream("inputday11.txt");
        largeExample = IOUtils.readLines(is, StandardCharsets.UTF_8);
        smallExample = IOUtils.readLines(Day8.class.getClassLoader().getResourceAsStream("inputday12_small.txt"), StandardCharsets.UTF_8);
    }

    @Test
    void one() {
        assertEquals(226, instance.one(smallExample));
    }

    @Test
    void testSmall() {
        /*start,HN,dc,HN,end
start,HN,dc,HN,kj,HN,end
start,HN,dc,end
start,HN,dc,kj,HN,end
start,HN,end
start,HN,kj,HN,dc,HN,end
start,HN,kj,HN,dc,end
start,HN,kj,HN,end
start,HN,kj,dc,HN,end
start,HN,kj,dc,end
start,dc,HN,end
start,dc,HN,kj,HN,end
start,dc,end
start,dc,kj,HN,end
start,kj,HN,dc,HN,end
start,kj,HN,dc,end
start,kj,HN,end
start,kj,dc,HN,end
start,kj,dc,end*/
        assertEquals(19, instance.one(smallExample));
    }


    @Test
    void two() {
    }

    @Test
    void computeEdges() {
        Map<String, Node> nodes = instance.parseNodes(smallExample);
        List<Edge> edges = instance.computeEdges(nodes, smallExample);
        assertEquals(7, edges.size());
    }

    @Test
    void parseNodes() {
        Map<String, Node> nodes = instance.parseNodes(smallExample);
        assertEquals(6, nodes.size());
        assertTrue(nodes.containsValue(new Node("start")));
        assertTrue(nodes.containsValue(new Node("end")));
        assertTrue(nodes.containsValue(new Node("A")));
        assertTrue(nodes.containsValue(new Node("b")));
        assertTrue(nodes.containsValue(new Node("c")));
        assertTrue(nodes.containsValue(new Node("d")));

    }

    @Test
    void computePaths() {
        Map<String, Node> nodes = instance.parseNodes(smallExample);
        List<Edge> edges = instance.computeEdges(nodes, smallExample);
        Map<Node, List<Edge>> startToEdge = instance.computeStartToEdge(edges);
        List<Node> startNode = List.of(nodes.get("start"));

        List<List<Node>> paths = instance.computePaths(startNode, startToEdge, new HashSet<>());
        Day12.printPaths(paths);
        assertEquals(10, paths.size());
    }

}

package nl.meine.adventofcode._2021;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {

    public int one(List<String> lines) {
        // haal alle nodes op
        Map<String, Node> nodes = parseNodes(lines);
        // dan per regel
        // haal twee nodes op
        // verbind ze met elkaar
        List<Edge> edges = computeEdges(nodes, lines);
        Map<Node, List<Edge>> startToEdge = computeStartToEdge(edges);
        Node startNode = nodes.get("start");
        List<List<Node>> paths = computePaths(List.of(startNode), startToEdge, new HashSet<>());
        return -1;
    }

    public List<List<Node>> computePaths(List<Node> current, Map<Node, List<Edge>> lookup, Set<String> alreadyComputed) {
        List<List<Node>> paths = new ArrayList<>();

        String id = getPathId(current);
        if (!alreadyComputed.contains(id)) {
            alreadyComputed.add(id);
        } else {
            int a = 0;
        }
        Node currentNode = current.get(current.size() - 1);
        List<Edge> possibleEdges = lookup.getOrDefault(currentNode, List.of());
        for (Edge e : possibleEdges) {
            List<Node> newPath = new ArrayList<>(current);
            newPath.add(e.end);
            if (e.end.key.equals("end")) {
                paths.add(newPath);
            } else {
                paths.addAll(computePaths(newPath, lookup, alreadyComputed));
            }

        }
        return paths;
    }

    public String getPathId(List<Node> nodes) {
        return nodes.stream().map(n -> n.key).collect(Collectors.joining());
    }


    public Map<Node, List<Edge>> computeStartToEdge(List<Edge> edges) {
        return edges
                .stream()
                .collect(
                        Collectors.groupingBy(
                                e -> e.start,
                                Collectors.mapping(e -> e, Collectors.toList())));
    }


    public List<Edge> computeEdges(Map<String, Node> nodes, List<String> input) {
        List<Edge> edges = input.stream()
                .flatMap(s -> Stream.of(
                        new Edge(nodes.get(getLeft(s)), nodes.get(getRight(s))),
                        new Edge(nodes.get(getRight(s)), nodes.get(getLeft(s))))
                )
                .collect(Collectors.toList());

        return edges;
    }

    public Map<String, Node> parseNodes(List<String> lines) {
        return lines.stream()
                .flatMap(s -> Stream.of(s.split("-")))
                .map(a -> new Node(a))
                .collect(Collectors.toMap(n -> n.key, n -> n, (x1, x2) -> x1));
    }

    public String getRight(String input) {
        return input.substring(input.indexOf("-") + 1);
    }

    public String getLeft(String input) {
        return input.substring(0, input.indexOf("-"));
    }


    public int two(List<String> input) {
        return -1;
    }

    public static void printPaths(List<List<Node>> paths) {
        for (List<Node> path : paths) {
            for (Node n : path) {
                System.out.print(n.key + "->");
            }
            System.out.println("");
        }
    }
}

class Node {
    boolean visited;
    boolean small;
    String key;

    List<Node> connected = new ArrayList<>();

    public Node(String key) {
        this.visited = false;
        this.small = StringUtils.isAllUpperCase(key);
        this.key = key;
    }

    public void addNode(Node n) {
        connected.add(n);
        //n.addNode(this);
    }

    public String getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return small == node.small && key.equals(node.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}

class Edge {
    Node start;
    Node end;

    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return start.equals(edge.start) && end.equals(edge.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}

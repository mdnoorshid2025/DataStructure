package ds.graphs;

import java.util.*;

public class Graph {

    // Depth First Print using Stack
    public static void depthFirstPrintUsingStack(
            Map<String, List<String>> graph,
            String source) {

        // Stack used for iterative DFS
        Deque<String> stack = new ArrayDeque<>();

        // Start traversal from source node
        stack.push(source);

        // Continue until stack becomes empty
        while (!stack.isEmpty()) {

            // Take the most recently added node
            String current = stack.pop();

            // Print/process current node
            System.out.print(current);

            // Get neighbors of current node
            List<String> neighbors = graph.get(current);

            // Add neighbors to stack
            for (String neighbor : neighbors) {
                stack.push(neighbor);
            }
        }
    }
    // Depth First Print using Recursion
    public static void depthFirstSearchUsingRecursion( Map<String, List<String>> graph,
                                                       String source){
        System.out.print(source);
        for(String neighbour : graph.get(source)){
            depthFirstSearchUsingRecursion(graph, neighbour);
        }

    }

    public static void main(String[] args) {

        Map<String, List<String>> graph = new HashMap<>();

        graph.put("a", List.of("c", "b"));
        graph.put("b", List.of("d"));
        graph.put("c", List.of("e"));
        graph.put("d", List.of("f"));
        graph.put("e", List.of());
        graph.put("f", List.of());

        depthFirstPrintUsingStack(graph, "a"); // abdfce
        System.out.println();
        depthFirstSearchUsingRecursion(graph, "a"); // abdfce
    }
}

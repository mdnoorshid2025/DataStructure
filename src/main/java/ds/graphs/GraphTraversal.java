package ds.graphs;

import java.util.*;

public class GraphTraversal {

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

    // Breadth First Print using Queue
    public static void breadthFirstSearchUsingQueue(Map<String, List<String>> graph,
                                                    String source) {
        Queue<String> queue = new ArrayDeque<>(); // Initialize queue
        queue.add(source); // Add source node to queue
        while (!queue.isEmpty()) { // Checking Queue for empty
            String current = queue.poll(); // Remove and return the head of the queue
            System.out.print(current); // Print current node
            for (String neighbour : graph.get(current)) { // Traversing neighbors
                queue.add(neighbour); // Add neighbors to queue
            }
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
        depthFirstSearchUsingRecursion(graph, "a"); // acebdf
        System.out.println();
        breadthFirstSearchUsingQueue(graph, "a"); // acbedf
    }
}

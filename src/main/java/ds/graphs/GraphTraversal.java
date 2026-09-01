package ds.graphs;

import java.util.*;

/**
 * Demonstrates different graph traversal algorithms:
 * - Depth First Search (DFS) using iterative approach with stack
 * - Depth First Search (DFS) using recursive approach
 * - Breadth First Search (BFS) using queue
 */
public class GraphTraversal {

    /**
     * Performs Depth First Search (DFS) traversal using an iterative approach with a stack.
     * DFS explores as far as possible along each branch before backtracking.
     * @param graph Adjacency list representation of the graph
     * @param source Starting node for traversal
     */
    public static void depthFirstPrintUsingStack(
            Map<String, List<String>> graph,
            String source) {

        // Stack used for iterative DFS - LIFO (Last In First Out) structure
        Deque<String> stack = new ArrayDeque<>();

        // Start traversal from source node
        stack.push(source);

        // Continue until stack becomes empty (all nodes visited)
        while (!stack.isEmpty()) {

            // Take the most recently added node (LIFO behavior)
            String current = stack.pop();

            // Print/process current node
            System.out.print(current);

            // Get neighbors of current node
            List<String> neighbors = graph.get(current);

            // Add neighbors to stack (will be processed in reverse order)
            for (String neighbor : neighbors) {
                stack.push(neighbor);
            }
        }
    }
    /**
     * Performs Depth First Search (DFS) traversal using recursion.
     * Recursively visits each neighbor before backtracking.
     * @param graph Adjacency list representation of the graph
     * @param source Current node being visited
     */
    public static void depthFirstSearchUsingRecursion( Map<String, List<String>> graph,
                                                       String source){
        // Print/process current node
        System.out.print(source);
        
        // Recursively visit each neighbor (depth-first exploration)
        for(String neighbour : graph.get(source)){
            depthFirstSearchUsingRecursion(graph, neighbour);
        }

    }

    /**
     * Performs Breadth First Search (BFS) traversal using a queue.
     * BFS explores all neighbors at current depth before moving to next depth level.
     * @param graph Adjacency list representation of the graph
     * @param source Starting node for traversal
     */
    public static void breadthFirstSearchUsingQueue(Map<String, List<String>> graph,
                                                    String source) {
        // Queue used for BFS - FIFO (First In First Out) structure
        Queue<String> queue = new ArrayDeque<>();
        queue.add(source); // Add source node to queue
        
        while (!queue.isEmpty()) { // Continue until queue becomes empty
            String current = queue.poll(); // Remove and return the head of the queue (FIFO)
            System.out.print(current); // Print current node
            
            // Add all neighbors to queue (will be processed in order)
            for (String neighbour : graph.get(current)) {
                queue.add(neighbour);
            }
        }

    }

    public static void main(String[] args) {
        // Build test graph:
        // a -> c, b
        // b -> d
        // c -> e
        // d -> f
        // e -> (no neighbors)
        // f -> (no neighbors)
        Map<String, List<String>> graph = new HashMap<>();

        graph.put("a", List.of("c", "b"));
        graph.put("b", List.of("d"));
        graph.put("c", List.of("e"));
        graph.put("d", List.of("f"));
        graph.put("e", List.of());
        graph.put("f", List.of());

        // Test iterative DFS: expected output "abdfce"
        depthFirstPrintUsingStack(graph, "a");
        System.out.println();
        
        // Test recursive DFS: expected output "acebdf"
        depthFirstSearchUsingRecursion(graph, "a");
        System.out.println();
        
        // Test BFS: expected output "acbedf"
        breadthFirstSearchUsingQueue(graph, "a");
    }
}

package ds.graphs;

import java.util.*;

/**
 * Demonstrates different graph traversal algorithms:
 * - Depth First Search (DFS) using iterative approach with stack
 * - Depth First Search (DFS) using recursive approach
 * - Breadth First Search (BFS) using queue
 * 
 * Problem: Visit all nodes in a graph starting from a given source node.
 * Graph traversal is fundamental for many graph algorithms including path finding,
 * cycle detection, and topological sorting.
 * 
 * COMPLEXITY ANALYSIS EXPLAINED IN DETAIL
 * ========================================
 * 
 * Time Complexity: O(V + E) where V = number of vertices, E = number of edges
 * 
 * WHY O(V + E)?
 * - We visit each vertex exactly once (V operations)
 * - For each vertex, we process all its edges (E operations total)
 * - Each edge is processed once (or twice for undirected graphs)
 * - Total work: V (vertices) + E (edges)
 * 
 * Example: Graph with 6 nodes and 5 edges
 * - We visit each of the 6 nodes once
 * - We process each of the 5 edges once
 * - Total operations: 6 + 5 = 11
 * 
 * Space Complexity: O(V) for the stack/queue/recursion stack
 * 
 * WHY O(V)?
 * - Stack/Queue: can hold at most V vertices in worst case
 * - Recursion stack: can go up to V levels deep in worst case (linear graph)
 * - Total: O(V)
 * 
 * DFS vs BFS:
 * - DFS (Depth First): Goes deep before wide. Uses stack (LIFO).
 *   Good for: Topological sort, cycle detection, maze solving
 * - BFS (Breadth First): Goes wide before deep. Uses queue (FIFO).
 *   Good for: Shortest path in unweighted graphs, level-order traversal
 */
public class GraphTraversal {

    /**
     * Performs Depth First Search (DFS) traversal using an iterative approach with a stack.
     * DFS explores as far as possible along each branch before backtracking.
     * 
     * Time Complexity: O(V + E) - visits each vertex once and processes each edge once
     * Space Complexity: O(V) - stack can hold at most V vertices in worst case
     * 
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
     * 
     * Time Complexity: O(V + E) - visits each vertex once and processes each edge once
     * Space Complexity: O(V) - recursion stack can go up to V levels deep in worst case
     * 
     * Note: This implementation does not handle cycles - for graphs with cycles,
     * a visited set is required to prevent infinite recursion.
     * 
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
     * 
     * Time Complexity: O(V + E) - visits each vertex once and processes each edge once
     * Space Complexity: O(V) - queue can hold at most V vertices in worst case
     * 
     * BFS is ideal for finding shortest paths in unweighted graphs since it explores
     * nodes level by level from the source.
     * 
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

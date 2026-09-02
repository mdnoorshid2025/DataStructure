package ds.graphs;

import java.util.*;

/**
 * Determines if a path exists between two nodes in a graph using different traversal strategies.
 * Implements both DFS (recursive) and BFS (iterative) approaches to find paths.
 */
public class HasPathGraph {

    /**
     * Checks if a path exists from source to destination using Depth First Search (DFS).
     * Uses recursive approach to explore each branch completely before backtracking.
     * 
     * Time Complexity: O(V + E) where V = number of vertices, E = number of edges
     * Space Complexity: O(V) for the recursion stack in worst case
     * 
     * @param graph Adjacency list representation of the graph
     * @param source Starting node
     * @param destination Target node to find
     * @return true if path exists, false otherwise
     */
    public static boolean hasPathDepthFirstSearchUsingRecursion(Map<String, List<String>> graph,
                                                                String source, String destination){
        // Base case: current node is the destination
        if(source.equals(destination))
            return true;
        
        // Recursively check each neighbor for path to destination
        for(String neighbour : graph.get(source)){
            if(hasPathDepthFirstSearchUsingRecursion(graph, neighbour, destination)){
                return true; // Path found through this neighbor
            }
        }
        
        // No path found from current node
        return false;
    }

    /**
     * Checks if a path exists from source to destination using Breadth First Search (BFS).
     * Uses iterative approach with queue to explore nodes level by level.
     * 
     * Time Complexity: O(V + E) where V = number of vertices, E = number of edges
     * Space Complexity: O(V) for the queue in worst case
     * 
     * @param graph Adjacency list representation of the graph
     * @param source Starting node
     * @param destination Target node to find
     * @return true if path exists, false otherwise
     */
    public static boolean hasPathUsingBreadthFirst( Map<String, List<String>> graph,
                                                       String source, String destination){
        Queue<String> queue = new ArrayDeque<>(); // FIFO queue for BFS
        queue.add(source); // Start from source node
        
        while(!queue.isEmpty()){ // Continue until queue is empty
            String nodeVal = queue.poll(); // Get next node to process
            
            // Check if we found the destination
            if(nodeVal.equals(destination))
                return true;
            
            // Add all neighbors to queue for exploration
            for(String neighbour : graph.get(nodeVal)){
                queue.add(neighbour);
            }
        }
        
        // Queue exhausted without finding destination
        return  false;

    }




    public static void main(String[] args) {
        // Build test graph:
        // f -> g, i
        // g -> h
        // h -> (no neighbors)
        // i -> g, k
        // j -> i
        // k -> (no neighbors)
        // Path from j to g: j -> i -> g (exists)
        Map<String, List<String>> graph = new HashMap<>();

        graph.put("f", List.of("g", "i"));
        graph.put("g", List.of("h"));
        graph.put("h", List.of());
        graph.put("i", List.of("g","k"));
        graph.put("j", List.of("i"));
        graph.put("k", List.of());
       
       // Test DFS: should return true (path exists)
       System.out.println(hasPathDepthFirstSearchUsingRecursion(graph, "j", "g"));
       
       // Test BFS: should return true (path exists)
       System.out.println(hasPathUsingBreadthFirst(graph, "j", "g"));

    }
}

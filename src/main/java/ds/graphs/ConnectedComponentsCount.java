package ds.graphs;

import java.util.*;

/**
 * Counts the number of connected components in an undirected graph.
 * A connected component is a set of nodes where each node is reachable from any other node in the same set.
 */
public class ConnectedComponentsCount {

    /**
     * Counts the number of connected components in the graph.
     * 
     * Time Complexity: O(V + E) where V = number of vertices, E = number of edges
     * Space Complexity: O(V) for the visited set and recursion stack
     * 
     * @param graph Adjacency list representation of the graph
     * @return Number of connected components
     */
    public static int countComponents(Map<Integer, List<Integer>> graph) {
        int count = 0; // Counter for connected components
        Set<Integer> visited = new HashSet<>(); // Track visited nodes to avoid cycles
        
        // Iterate through all nodes in the graph
        for(Integer node : graph.keySet()){
            // If explore returns true, we found a new unvisited component
            if(explore(graph, node, visited)){
                count++;
            }
        }

        return count;
    }

    /**
     * Recursively explores all nodes in the current connected component.
     * Uses DFS (Depth-First Search) to traverse the component.
     * 
     * Time Complexity: O(V + E) for the entire graph traversal
     * Space Complexity: O(V) for the recursion stack in worst case
     * 
     * @param graph Adjacency list representation of the graph
     * @param current Current node being explored
     * @param visited Set of already visited nodes
     * @return true if this is a new unvisited component, false if already visited
     */
    private static boolean explore(Map<Integer, List<Integer>> graph, int current, Set<Integer> visited) {
        // If node is already visited, it's part of a component we've already counted
        if(visited.contains(current)) return false;
        
        // Mark current node as visited
        visited.add(current);
        
        // Recursively visit all neighbors (DFS traversal)
        for(int neighbour : graph.get(current)){
            explore(graph, neighbour, visited);
        }
        
        // Return true to indicate we've completed exploring a new component
        return true;
    }




    public static void main(String[] args) {
        // Build test graph with 3 connected components:
        // Component 1: {3} (isolated node)
        // Component 2: {4, 5, 6, 7, 8} (connected cluster)
        // Component 3: {1, 2} (connected pair)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(3, List.of()); // Node 3 is isolated
        graph.put(4, List.of(6));
        graph.put(6, List.of(4, 5, 7, 8)); // Central hub node
        graph.put(8, List.of(6));
        graph.put(7, List.of(6));
        graph.put(5, List.of(6));
        graph.put(1, List.of(2));
        graph.put(2, List.of(1));

        // Expected output: 3 (three connected components)
        System.out.println(countComponents(graph));
    }
}
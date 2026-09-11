package ds.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Finds the size of the largest connected component in an undirected graph.
 * A connected component is a set of nodes where each node is reachable from any other node in the same set.
 * Uses DFS traversal to explore each component and track the maximum size found.
 * 
 * COMPLEXITY ANALYSIS EXPLAINED IN DETAIL
 * ========================================
 * 
 * PROBLEM: Find the size of the largest connected group of nodes in a graph.
 * 
 * Time Complexity: O(V + E) where V = number of vertices, E = number of edges
 * 
 * WHY O(V + E)?
 * - We iterate through all V vertices in the outer loop
 * - For each unvisited vertex, we do DFS to explore its component
 * - During DFS, we visit each vertex and edge in that component exactly once
 * - Total work: V (visiting each node) + E (processing each edge)
 * 
 * Example: Graph with 8 nodes and 7 edges
 * - We check each of the 8 nodes once
 * - DFS explores each component, visiting all edges
 * - Total operations: 8 + 7 = 15
 * 
 * Space Complexity: O(V) for the visited set and recursion stack
 * 
 * WHY O(V)?
 * - Visited set: stores at most V entries (one per node)
 * - Recursion stack: in worst case (linear graph), depth = V
 * - Total: O(V) + O(V) = O(V)
 * 
 * ALGORITHM EXPLANATION:
 * 1. Iterate through all nodes in the graph
 * 2. For each unvisited node, start DFS to count component size
 * 3. DFS returns the size of the current component
 * 4. Track the maximum size found across all components
 * 5. Visited nodes are skipped in subsequent iterations
 */
public class LargestComponent {

    /**
     * Finds the size of the largest connected component in the graph.
     * Iterates through all nodes and uses DFS to count component sizes.
     * 
     * Time Complexity: O(V + E) where V = number of vertices, E = number of edges
     * Space Complexity: O(V) for the visited set and recursion stack
     * 
     * @param graph Adjacency list representation of the graph
     * @return Size of the largest connected component
     */
    public static int getLargestComponent(Map<Integer, List<Integer>> graph) {
        int largest = 0; // Track the maximum component size found
        HashSet<Integer> visited = new HashSet<>(); // Track visited nodes to avoid cycles
        
        // Iterate through all nodes in the graph
        for (int node : graph.keySet()) {
           // Explore the component starting from this node
           int count = explore(graph, node, visited);
           System.out.println("count from explore "+count);
           
           // Update largest if current component is bigger
           if(count > largest) {
            largest = count;
           }
        }
        
        System.out.println("largest "+largest);
        return largest;
    }


    /**
     * Recursively explores all nodes in the current connected component and counts its size.
     * Uses DFS (Depth-First Search) to traverse the component.
     * 
     * Time Complexity: O(V + E) for the entire graph traversal
     * Space Complexity: O(V) for the recursion stack in worst case
     * 
     * @param graph Adjacency list representation of the graph
     * @param current Current node being explored
     * @param visited Set of already visited nodes
     * @return Size of the component (number of nodes)
     */
    public static int explore(Map<Integer, List<Integer>> graph, int current, HashSet<Integer> visited) {
        // If already visited, don't count this node (prevents cycles)
        if(visited.contains(current)) { return 0; }
        
        // Mark current node as visited
        visited.add(current);
        
        // Start with size 1 (the current node itself)
        int size = 1;
        
        // Recursively count all neighbors and add to total size
        for(Integer neighbour : graph.get(current)) {
             size += explore(graph, neighbour, visited);
        }
        
        return size;
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

        // Expected output: 5
        System.out.println(getLargestComponent(graph));

    }
}

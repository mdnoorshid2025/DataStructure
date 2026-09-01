package ds.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Finds the size of the largest connected component in an undirected graph.
 * A connected component is a set of nodes where each node is reachable from any other node in the same set.
 * Uses DFS traversal to explore each component and track the maximum size found.
 */
public class LargestComponent {

    /**
     * Finds the size of the largest connected component in the graph.
     * Iterates through all nodes and uses DFS to count component sizes.
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

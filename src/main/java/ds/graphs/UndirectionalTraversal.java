package ds.graphs;

import java.util.*;

/**
 * Finds if a path exists between two nodes in an undirected graph.
 * Converts edge list to adjacency list and uses DFS with visited set to avoid cycles.
 * 
 * Example: edges = [['i','j'], ['k','i'], ['m','k'], ['k','l'], ['o','n']]
 * undirectedPath(edges, 'j', 'm') -> true (path: j -> i -> k -> m)
 */
public class UndirectionalTraversal {
    /**
     * Determines if a path exists between two nodes in an undirected graph.
     * @param edges List of edge pairs representing undirected connections
     * @param nodeA Starting node
     * @param nodeB Target node
     * @return true if path exists, false otherwise
     */
    public static boolean undirectedPath(List<List<String>> edges, String nodeA, String nodeB){
        // Convert edge list to adjacency list representation
        Map<String, List<String>> graph = buildGraph(edges);
        // Use DFS with visited set to find path (handles cycles)
        return hasPath(graph, nodeA, nodeB, new HashSet<>());
    }

    /**
     * Recursively searches for a path from source to destination using DFS.
     * Uses visited set to prevent infinite loops in undirected graphs.
     * @param graph Adjacency list representation
     * @param src Current node being explored
     * @param dest Target node to find
     * @param visited Set of already visited nodes
     * @return true if path exists, false otherwise
     */
    public  static boolean hasPath(Map<String, List<String>> graph, String src, String dest, Set<String> visited) {
        // Base case: found the destination
        if(src.equals(dest)){
            return true;
        }
        
        // Skip if already visited (prevents cycles in undirected graph)
        if(visited.contains(src)){
            return false;
        }
        
        // Mark current node as visited
        visited.add(src);
        
        // Recursively check all neighbors for path to destination
        for(String neighbour : graph.get(src)){
          if(hasPath(graph, neighbour, dest, visited)){
              return true; // Path found through this neighbor
        }
    }
        
        // No path found from current node
        return false;
    }

    /**
     * Builds an adjacency list representation from an edge list.
     * For undirected graphs, each edge is added in both directions.
     * @param edges List of edge pairs [node1, node2]
     * @return Adjacency list map where key=node, value=list of neighbors
     */
    public static Map<String,List<String>> buildGraph(List<List<String>> edges){
        Map<String,List<String>> graph = new HashMap<>();
        
        // Process each edge in the list
        for(List<String> edge : edges){
            String a = edge.get(0);
            String b = edge.get(1);

            // Initialize adjacency list for node a if not present
            if(!graph.containsKey(a)){
                graph.put(a, new ArrayList<>());
            }
            // Initialize adjacency list for node b if not present
            if(!graph.containsKey(b)){
                graph.put(b, new ArrayList<>());
            }
            
            // Add edge in both directions (undirected graph)
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return  graph;
    }

    public static void main(String[] args) {
       // Build test edges for undirected graph:
       // Component 1: i-j-k-l-m (connected)
       // Component 2: o-n (separate component)
       // Path from j to m exists: j -> i -> k -> m
       List<List<String>> edges = new ArrayList<>();
       edges.add(List.of("i", "j"));
       edges.add(List.of("k", "i"));
       edges.add(List.of("m", "k"));
       edges.add(List.of("k", "l"));
       edges.add(List.of("o", "n"));
       
       // Expected output: true (path exists between j and m)
       System.out.println(undirectedPath(edges, "j", "m"));
    }
}

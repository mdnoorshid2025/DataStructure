package ds.graphs;

import java.util.*;

/**
 * Finds the shortest path between two nodes in an unweighted undirected graph using BFS.
 * 
 * Problem: Given an edge list and two nodes, find the minimum number of edges
 * required to travel from nodeA to nodeB. If no path exists, return -1.
 * 
 * This uses Breadth First Search (BFS) which guarantees finding the shortest path
 * in unweighted graphs since it explores nodes level by level from the source.
 * 
 * COMPLEXITY ANALYSIS EXPLAINED IN DETAIL
 * ========================================
 * 
 * PROBLEM: Find the minimum number of edges between two nodes in an unweighted graph.
 * 
 * Time Complexity: O(V + E) where V = number of vertices, E = number of edges
 * 
 * WHY O(V + E)?
 * - BFS visits each vertex at most once (V operations)
 * - For each visited vertex, we process all its edges (E operations total)
 * - Each edge is processed at most once
 * - Total work: V (vertices visited) + E (edges processed)
 * 
 * Example: Graph with 5 nodes and 5 edges
 * - We visit each of the 5 nodes at most once
 * - We process each of the 5 edges once
 * - Total operations: 5 + 5 = 10
 * 
 * Space Complexity: O(V) for the queue and visited set
 * 
 * WHY O(V)?
 * - Queue: can hold up to V vertices in worst case
 * - Visited set: stores at most V entries (one per node)
 * - Total: O(V) + O(V) = O(V)
 * 
 * WHY BFS FOR SHORTEST PATH?
 * - BFS explores nodes level by level from the source
 * - Level 0: source node (distance 0)
 * - Level 1: all nodes 1 edge away
 * - Level 2: all nodes 2 edges away
 * - First time we reach the destination, it's guaranteed to be the shortest path
 * - DFS does NOT guarantee shortest path (might go deep before finding destination)
 */
public class ShortestPath {

    /**
     * Record to store a node along with its distance from the source.
     * Used in BFS to track how many edges we've traversed to reach each node.
     */
    public record CharIntPair(char node, int distance) {}

    /**
     * Finds the shortest path between two nodes using BFS traversal.
     * 
     * Time Complexity: O(V + E) - visits each vertex once and processes each edge once
     * Space Complexity: O(V) - queue and visited set can hold at most V vertices
     * 
     * @param edges List of edge pairs representing undirected connections
     * @param nodeA Starting node
     * @param nodeB Target node
     * @return Minimum number of edges from nodeA to nodeB, or -1 if no path exists
     */
    public static int shortestPath(List<List<Character>> edges, char nodeA, char nodeB) {
      // Convert edge list to adjacency list representation
      Map<Character, List<Character>> graph = buildGraph(edges);
      Set<Character> visited = new HashSet<>(); // Track visited nodes to avoid cycles

      // BFS queue stores (node, distance) pairs
      Queue<CharIntPair> queue = new ArrayDeque<>();
      queue.add(new CharIntPair(nodeA, 0)); // Start from source with distance 0
      visited.add(nodeA);

      // BFS traversal - explores nodes level by level
      while(!queue.isEmpty()){
          CharIntPair charIntPair = queue.poll();
          char node = charIntPair.node;

          // Found the target node - return the distance
          if(node == nodeB){ 
              return charIntPair.distance;
          }

          // Explore all unvisited neighbors
          for(char neighbor : graph.get(node)){
              if(!visited.contains(neighbor)){
                  visited.add(neighbor);
                  // Add neighbor with incremented distance
                  queue.add(new CharIntPair(neighbor, charIntPair.distance + 1));
              }
          }
      }

        // Queue exhausted without finding target - no path exists
        return -1;
    }


    /**
     * Builds an adjacency list representation from an edge list.
     * For undirected graphs, each edge is added in both directions.
     * 
     * Time Complexity: O(E) where E = number of edges
     * Space Complexity: O(V + E) for the adjacency list
     * 
     * @param edges List of edge pairs [node1, node2]
     * @return Adjacency list map where key=node, value=list of neighbors
     */
    public static Map<Character, List<Character>> buildGraph(List<List<Character>> edges) {
        Map<Character, List<Character>> graph = new HashMap<>();
        
        // Process each edge in the list
        for(List<Character> edge : edges){
            char a = edge.get(0);
            char b = edge.get(1);

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
        return graph;
    }


    public static void main(String[] args) {
        // Build test edges for undirected graph:
        // w -- x -- y -- z -- v
        // |____________|
        // Shortest path from w to v: w -> v (direct edge, distance = 1)
        List<List<Character>> edges = new ArrayList<>();
        edges.add(List.of('w','x'));
        edges.add(List.of('x','y'));
        edges.add(List.of('z','y'));
        edges.add(List.of('z','v'));
        edges.add(List.of('w','v'));
        
        // Expected output: 1 (direct edge from w to v)
        System.out.println(shortestPath(edges, 'w', 'v'));
    }


}

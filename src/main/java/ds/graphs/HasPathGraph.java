package ds.graphs;

import java.util.*;

public class HasPathGraph {

    // Depth First Print using Recursion
    public static boolean hasPathDepthFirstSearchUsingRecursion(Map<String, List<String>> graph,
                                                                String source, String destination){
        if(source.equals(destination)) // Check if current node is destination
            return true;
        for(String neighbour : graph.get(source)){ // Traversing neighbors
            if(hasPathDepthFirstSearchUsingRecursion(graph, neighbour, destination)){ // Recursively check for path
                return true;
            }
        }
        return false;
    }

    // Breadth First Print using Queue
    public static boolean hasPathUsingBreadthFirst( Map<String, List<String>> graph,
                                                       String source, String destination){
        Queue<String> queue = new ArrayDeque<>(); // Initialize queue
        queue.add(source); // Add source node to queue
        while(!queue.isEmpty()){ // Continue until queue becomes empty
            String nodeVal = queue.poll(); // Remove and return the head of the queue
            if(nodeVal.equals(destination)) // Check if current node is destination
                return true;
            for(String neighbour : graph.get(nodeVal)){ // Traversing neighbors
                queue.add(neighbour); // Add neighbors to queue
            }

        }
        return  false;

    }




    public static void main(String[] args) {

        Map<String, List<String>> graph = new HashMap<>();

        graph.put("f", List.of("g", "i"));
        graph.put("g", List.of("h"));
        graph.put("h", List.of());
        graph.put("i", List.of("g","k"));
        graph.put("j", List.of("i"));
        graph.put("k", List.of());
       System.out.println(hasPathDepthFirstSearchUsingRecursion(graph, "j", "g"));
       System.out.println(hasPathUsingBreadthFirst(graph, "j", "g"));

    }
}

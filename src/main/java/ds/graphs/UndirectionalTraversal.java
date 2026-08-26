package ds.graphs;


/*const edges = [
        ['i', 'j'],
        ['k', 'i'],
        ['m', 'k'],
        ['k', 'l'],
        ['o', 'n']
        ];

undirectedPath(edges, 'j', 'm'); // -> true*/
import java.util.*;
public class UndirectionalTraversal {
    public static boolean undirectedPath(List<List<String>> edges, String nodeA, String nodeB){
        Map<String, List<String>> graph = buildGraph(edges);
        return hasPath(graph, nodeA, nodeB,new HashSet<>());
    }

    public  static boolean hasPath(Map<String, List<String>> graph, String src, String dest, Set<String> visited) {
        if(src.equals(dest)){
            return true;
        }
        if(visited.contains(src)){
            return false;
        }
        visited.add(src);
        for(String neighbour : graph.get(src)){
          if(hasPath(graph, neighbour, dest, visited)){
              return true;
        }
    }
        return false;
    }

    public static Map<String,List<String>> buildGraph(List<List<String>> edges){
        Map<String,List<String>> graph = new HashMap<>();
        for(List<String> edge : edges){
            String a = edge.get(0);
            String b = edge.get(1);

            if(!graph.containsKey(a)){
                graph.put(a, new ArrayList<>());
            }
            if(!graph.containsKey(b)){
                graph.put(b, new ArrayList<>());
            }
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return  graph;
    }

    public static void main(String[] args) {
       List<List<String>> edges = new ArrayList<>();
       edges.add(List.of("i", "j"));
       edges.add(List.of("k", "i"));
       edges.add(List.of("m", "k"));
       edges.add(List.of("k", "l"));
       edges.add(List.of("o", "n"));
       System.out.println(undirectedPath(edges, "j", "m"));
    }
}

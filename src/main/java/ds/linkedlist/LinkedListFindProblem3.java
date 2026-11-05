package ds.linkedlist;

/**
 * Problem: Find if a target value exists in a linked list
 * 
 * Given the head of a linked list and a target value, determine if the target exists in the list.
 * Implement both iterative and recursive solutions.
 * 
 * Time Complexity:
 * - Iterative: O(n) where n is the number of nodes
 * - Recursive: O(n) where n is the number of nodes
 * 
 * Space Complexity:
 * - Iterative: O(1) constant space
 * - Recursive: O(n) due to call stack
 */
public class LinkedListFindProblem3 {

    // Iterative approach: traverse the list and compare each node's value with target
    public static boolean findByIteration(Node<String> head, String target){
      Node<String> current = head; // Start from the head
      while(current != null){ // Traverse until end of list
          if(current.value.equals(target)) return true; // Target found
          current = current.next; // Move to next node
      }
      return false; // Target not found after traversing entire list
    }

    // Recursive approach: check current node, then recursively check the rest
    public static boolean findByRecursion(Node<String> head, String target){
        if(head == null) return false; // Base case: end of list, target not found
        if(head.value.equals(target)) return true; // Target found at current node
        return findByRecursion(head.next,target); // Recursively check remaining nodes
    }

    public static void main(String[] args) {
        // Create linked list: a -> b -> c -> d
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");

        a.next = b;
        b.next = c;
        c.next = d;

//        System.out.println(findByIteration(a, "c")); // true
//        System.out.println(findByIteration(a, "d")); // true
        System.out.println(findByRecursion(a, "c")); // true
        System.out.println(findByRecursion(a, "e")); // false

    }
}

package ds.linkedlist;

/**
 * Problem: Reverse a linked list
 * 
 * Given the head of a linked list, reverse the list and return the new head.
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
public class ReverseLinkedListProblem5 {

    // Iterative approach: use three pointers (prev, current, next) to reverse links
    public static Node<String> reverseUsingIteration(Node<String> head){
     Node<String> prev = null; // Previous node, initially null
     Node<String> current = head; // Current node, start at head
     while(current != null){ // Traverse until end of list
         Node<String> next = current.next; // Store next node before changing link
         current.next = prev; // Reverse the link: point current to previous
         prev = current; // Move prev forward
         current = next; // Move current forward
     }
     return prev; // prev is now the new head of reversed list
    }

    // Recursive approach: reverse link at each node and recurse on the rest
    public static Node<String> reverseUsingRecursion(Node<String> head, Node<String> prev){
        if(head == null ) return prev; // Base case: end of list, return new head
        Node<String> next = head.next; // Store next node before changing link
        head.next = prev; // Reverse the link: point current to previous
        return reverseUsingRecursion(next,head); // Recurse on remaining nodes
    }

    public static void main(String[] args) {
        // Create linked list: a -> b -> c -> d -> e -> f
        // After reverse:    f -> e -> d -> c -> b -> a
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");
        Node<String> e = new Node<>("e");
        Node<String> f = new Node<>("f");

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;

//        System.out.println(reverseUsingIteration(a).value); // f (new head after reversal)
        System.out.println(reverseUsingRecursion(a,null).value); // f (new head after reversal)
    }
}

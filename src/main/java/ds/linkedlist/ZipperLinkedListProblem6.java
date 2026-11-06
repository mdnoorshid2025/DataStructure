package ds.linkedlist;

/**
 * Problem: Zipper Merge Two Linked Lists
 * 
 * Given two linked lists, merge them together in a zipper pattern (alternating nodes).
 * The first node should be from the first list, the second from the second list,
 * the third from the first list, and so on.
 * 
 * Example:
 * List 1: a -> b -> c
 * List 2: x -> y -> z
 * Result: a -> x -> b -> y -> c -> z
 * 
 * Time Complexity:
 * - Iterative: O(min(n, m)) where n and m are lengths of the two lists
 * - Recursive: O(min(n, m))
 * 
 * Space Complexity:
 * - Iterative: O(1) - only uses constant extra space
 * - Recursive: O(min(n, m)) - recursion stack depth
 */
public class ZipperLinkedListProblem6 {

    public static Node<String> zipperListUsingIteration(Node<String> head1, Node<String> head2) {
        // Start with head1 as the tail of the merged list
        Node<String> tail = head1;
        Node<String> current1 = head1.next;
        Node<String> current2 = head2;
        int count = 0;
        
        // Alternate between lists until one is exhausted
        while (current1 != null && current2 != null) {
            if (count % 2 == 0) {
                // Even count: take from list 2
                tail.next = current2;
                current2 = current2.next;
            } else {
                // Odd count: take from list 1
                tail.next = current1;
                current1 = current1.next;
            }
            tail = tail.next;
            count++;
        }
        
        // Append remaining nodes from whichever list still has elements
        if(current1 != null) tail.next = current1;
        if(current2 != null) tail.next = current2;
        
        return head1;
    }

    public static Node<String> zipperListUsingRecursion(Node<String> head1 , Node<String> head2){
        // Base cases: if either list is null, return the other
        if(head1 == null && head2 == null) return null;
        if(head1 == null) return head2;
        if(head2 == null) return head1;
        
        // Store the next nodes before modifying pointers
        Node<String> next1 = head1.next;
        Node<String> next2 = head2.next;
        
        // Link head1 to head2, then recursively merge the rest
        head1.next = head2;
        head2.next = zipperListUsingRecursion(next1,next2);
        
        return head1;
    }

    public static void main(String[] args) {
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        a.next = b;
        b.next = c;
        // a -> b -> c
        Node<String> x = new Node<>("x");
        Node<String> y = new Node<>("y");
        Node<String> z = new Node<>("z");
        x.next = y;
        y.next = z;
        // x -> y -> z
//      System.out.println(zipperListUsingIteration(a, x).value); //a
        System.out.println(zipperListUsingRecursion(a, x).value); //a
    }
}

package ds.linkedlist;

/**
 * Problem: Get the value of a node at a specific index in a linked list
 * 
 * Given the head of a linked list and an index, return the value at that index.
 * If the index is out of bounds, return null.
 * Implement both iterative and recursive solutions.
 * 
 * Time Complexity:
 * - Iterative: O(n) where n is the index value (worst case)
 * - Recursive: O(n) where n is the index value (worst case)
 * 
 * Space Complexity:
 * - Iterative: O(1) constant space
 * - Recursive: O(n) due to call stack
 */
public class NodeValueProblem4 {

    // Iterative approach: traverse while counting until reaching the target index
    public static String getValueUsingIteration(Node<String> head, int index){
        Node<String> current = head; // Start from head
        int count = 0; // Track current position
        while(current != null){ // Traverse until end of list
            if(count == index) return current.value; // Found the index
            count++; // Increment position counter
            current = current.next; // Move to next node
        }
        return null; // Index out of bounds
    }

    // Recursive approach: decrement index until it reaches 0, then return current node's value
    public static String getValueUsingRecursion(Node<String> head, int index){
        if(head == null) return null; // Base case: end of list, index out of bounds
        if(index == 0 ) return head.value; // Base case: reached the target index
        return getValueUsingRecursion(head.next,index - 1); // Recursive call with decremented index
    }

    public static void main(String[] args) {
        // Create linked list: a -> b -> c -> d
        // Index:           0    1    2    3
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");

        a.next = b;
        b.next = c;
        c.next = d;
        System.out.println(getValueUsingIteration(a, 2)); // Output: c
        System.out.println(getValueUsingRecursion(a, 2)); // Output: c

    }
}

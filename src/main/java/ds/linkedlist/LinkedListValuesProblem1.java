package ds.linkedlist;

import java.util.ArrayList;
import java.util.List;

// Problem: Given the head of a linked list, return a list containing all values in the linked list
public class LinkedListValuesProblem1 {

    // Time: O(n), Space: O(n)
    public static List<String> linkedListValuesUsingIteration(Node<String> head){
      Node<String> current = head; // Start from head
      List<String> values = new ArrayList<>();
      while(current != null){ // Traverse until end
          values.add(current.value); // Collect value
          current = current.next; // Move to next node
      }
     return values;
    }

    // Time: O(n), Space: O(n) - O(n) for list + O(n) call stack
    public static List<String> linkedListValuesUsingRecursion(Node<String> head){
        List<String> values = new ArrayList<>();
        if(head == null) return values; // Base case: empty list
        values.add(head.value); // Add current value
        values.addAll(linkedListValuesUsingRecursion(head.next)); // Recurse for rest
        return values;
    }

    public static void main(String[] args) {
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");
        a.next = b; // Link nodes: a -> b -> c -> d
        b.next = c;
        c.next = d;
        List<String> values = linkedListValuesUsingRecursion(a);
        System.out.println(values);
    }
}



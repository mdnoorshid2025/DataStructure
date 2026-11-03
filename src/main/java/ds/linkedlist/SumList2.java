package ds.linkedlist;

// Problem: Given the head of a linked list with integer values, return the sum of all values
public class SumList2 {
    // Time: O(n), Space: O(1)
    public static int sumListUsingIteration(Node<Integer> head){
        int sum = 0;
        Node<Integer> current = head; // Start from head
        while(current != null){ // Traverse until end
            sum += current.value; // Add value to sum
            current = current.next; // Move to next node
        }
        return sum;
    }

    // Time: O(n), Space: O(n) - call stack
    public  static int sumListUsingRecursion(Node<Integer> head){
        int sum = 0;
        if(head == null) return 0; // Base case: empty list
        sum += head.value; // Add current value
        return sum + sumListUsingRecursion(head.next); // Recurse for rest
    }

    public static void main(String[] args) {
        Node<Integer> a = new Node<>(2);
        Node<Integer> b = new Node<>(8);
        Node<Integer> c = new Node<>(3);
        Node<Integer> d = new Node<>(-1);
        Node<Integer> e = new Node<>(7);

        a.next = b; // Link nodes: 2 -> 8 -> 3 -> -1 -> 7
        b.next = c;
        c.next = d;
        d.next = e;
        System.out.println(sumListUsingRecursion(a));
    }
}

package ds.dynamicprogramming.memoization;

import java.util.HashMap;
import java.util.Map;

public class GridTraveler {
    // Grid Traveler Problem: Find number of ways to travel from top-left to bottom-right of an r x c grid
    // You can only move down or right at each step

    // Recursive solution WITHOUT memoization
    // Time Complexity: O(2^(r+c)) - exponential, each call branches into 2 calls
    // Space Complexity: O(r+c) - recursion stack depth
    public static int gridTravelerWithoutMemoization(int r, int c) {
        if(r == 1 && c == 1) return 1;  // Reached destination - 1 way
        if(r == 0 || c == 0) return 0;  // Out of bounds - 0 ways
        return gridTravelerWithoutMemoization(r - 1, c) + gridTravelerWithoutMemoization(r, c - 1);
    }

    // Wrapper method to initialize memoization map and call the recursive function
    // Returns long to handle large results for bigger grids
    public static long getValWithMemoization(int r , int c){
        Map<String, Long> memo = new HashMap<>();  // Memoization table: stores computed results
        return gridTravelerWithMemoization(r, c, memo);
    }

    // Recursive solution WITH memoization
    // Time Complexity: O(r*c) - each unique position computed only once
    // Space Complexity: O(r*c) - memoization map stores all unique positions
    public static long gridTravelerWithMemoization(int r, int c , Map<String, Long> memo) {
        String position = r + "," + c;  // Create unique key for current position
        
        // Check if result is already computed and stored in memo
        if(memo.containsKey(position)) { return memo.get(position); }
        
        // Base cases (same as without memoization)
        if(r == 1 && c == 1) return 1;  // Reached destination
        if(r == 0 || c == 0) return 0;  // Out of bounds
        
        // Compute result, store in memo, and return
        memo.put(position, gridTravelerWithMemoization(r - 1, c, memo) + gridTravelerWithMemoization(r, c - 1, memo));
        return memo.get(position);
    }

    public static void main(String[] args) {
        // Test cases WITHOUT memoization (slow for larger grids)
//        System.out.println(gridTravelerWithoutMemoization(1, 1));  // Output: 1
//        System.out.println(gridTravelerWithoutMemoization(2, 3));  // Output: 3
//        System.out.println(gridTravelerWithoutMemoization(3, 2));  // Output: 3
//        System.out.println(gridTravelerWithoutMemoization(3, 3));  // Output: 6
//        System.out.println(gridTravelerWithoutMemoization(18, 18)); // Very slow/exponential time
        
        // Test cases WITH memoization (fast even for larger grids)
        System.out.println(getValWithMemoization(1, 1));  // Output: 1
        System.out.println(getValWithMemoization(2, 3));  // Output: 3
        System.out.println(getValWithMemoization(3, 2));  // Output: 3
        System.out.println(getValWithMemoization(3, 3));  // Output: 6
        System.out.println(getValWithMemoization(18, 18)); // Output: 2333606220 (computed quickly)
    }
}

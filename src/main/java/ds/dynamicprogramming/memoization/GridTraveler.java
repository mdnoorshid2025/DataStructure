package ds.dynamicprogramming.memoization;

import java.util.HashMap;
import java.util.Map;

public class GridTraveler {
    /*
     * COMPLEXITY ANALYSIS EXPLAINED IN DETAIL
     * ========================================
     * 
     * PROBLEM: Find number of ways to travel from top-left to bottom-right of an r x c grid.
     * You can only move down or right at each step.
     * 
     * WITHOUT MEMOIZATION (gridTravelerWithoutMemoization):
     * ------------------------------------------------------
     * Time Complexity: O(2^(r+c)) - exponential
     * 
     * WHY EXPONENTIAL?
     * Example: gridTraveler(2, 3)
     * 
     * Recursion tree:
     * grid(2,3)
     * ├── grid(1,3) [move down]
     * │   ├── grid(0,3) = 0 (out of bounds)
     * │   └── grid(1,2) [move right]
     * │       ├── grid(0,2) = 0
     * │       └── grid(1,1) = 1 (destination!)
     * └── grid(2,2) [move right]
     *     ├── grid(1,2) [REPEATED!]
     *     └── grid(2,1) [move right]
     *         ├── grid(1,1) = 1 (destination!)
     *         └── grid(2,0) = 0
     * 
     * Branching factor = 2 (each call splits into 2: down or right)
     * Tree height = r + c (need to move r times down AND c times right)
     * Total nodes = 2^(r+c) (exponential growth)
     * 
     * THE PROBLEM: Same subproblems like grid(1,2) are computed multiple times!
     * For grid(18,18): 2^36 ≈ 6.8 × 10^10 operations (very slow!)
     * 
     * Space Complexity: O(r+c)
     * WHY? Recursion stack depth is r+c (maximum moves to reach destination)
     * 
     * WITH MEMOIZATION (gridTravelerWithMemoization):
     * ------------------------------------------------
     * Time Complexity: O(r × c) - polynomial
     * 
     * WHY POLYNOMIAL?
     * Memoization caches results, so each unique position is computed ONLY ONCE.
     * 
     * Example with memo:
     * grid(2,3) - memo: {}
     * ├── grid(1,3) - memo: {}
     * │   └── grid(1,2) - memo: {}
     * │       └── grid(1,1) = 1 → memo["1,1"] = 1
     * │       └── memo["1,2"] = grid(0,2) + grid(1,1) = 1
     * │   └── memo["1,3"] = grid(0,3) + grid(1,2) = 1
     * └── grid(2,2) - memo: {"1,1":1, "1,2":1, "1,3":1}
     *     ├── grid(1,2) ✅ CACHE HIT! Returns 1 immediately
     *     └── grid(2,1) - memo: {...}
     *         └── grid(1,1) ✅ CACHE HIT! Returns 1 immediately
     *         └── memo["2,1"] = grid(1,1) + grid(2,0) = 1
     *     └── memo["2,2"] = grid(1,2) + grid(2,1) = 2
     * └── memo["2,3"] = grid(1,3) + grid(2,2) = 3
     * 
     * Key insight: Once grid(r,c) is computed, we never recompute it.
     * 
     * Calculation:
     * - Unique subproblems: r × c (all possible positions in grid)
     * - Work per subproblem: O(1) (just addition and lookup)
     * - Total work: (r × c) × 1 = O(r × c)
     * 
     * For grid(18,18): 18 × 18 = 324 operations (instant!)
     * 
     * Space Complexity: O(r × c)
     * WHY?
     * - Memo map: stores r × c entries (one per grid position)
     * - Recursion stack: O(r+c) depth
     * - Total: O(r × c) + O(r+c) = O(r × c)
     * 
     * SUMMARY:
     * Memoization trades O(r × c) space for O(2^(r+c)) → O(r × c) time improvement.
     * Each subproblem solved exactly once instead of being recomputed.
     */

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

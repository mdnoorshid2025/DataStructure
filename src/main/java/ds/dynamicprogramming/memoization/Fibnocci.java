package ds.dynamicprogramming.memoization;

import java.util.HashMap;
import java.util.Map;

public class Fibnocci {

    /*
     * COMPLEXITY ANALYSIS EXPLAINED IN DETAIL
     * ========================================
     * 
     * PROBLEM: Calculate the nth Fibonacci number where:
     * fib(1) = 1, fib(2) = 1, fib(n) = fib(n-1) + fib(n-2)
     * 
     * WITHOUT MEMOIZATION (fibWithoutMemoization):
     * ------------------------------------------------
     * Time Complexity: O(2^n) - exponential
     * 
     * WHY EXPONENTIAL?
     * Example: fib(5)
     * 
     * Recursion tree:
     * fib(5)
     * ├── fib(4)
     * │   ├── fib(3)
     * │   │   ├── fib(2) = 1
     * │   │   └── fib(1) = 1
     * │   └── fib(2) = 1
     * └── fib(3)
     *     ├── fib(2) = 1
     *     └── fib(1) = 1
     * 
     * Branching factor = 2 (each call splits into 2 calls)
     * Tree height = n
     * Total nodes = 2^n (exponential growth)
     * 
     * THE PROBLEM: Same subproblems are computed multiple times!
     * fib(3) appears twice, fib(2) appears three times in this tree.
     * For fib(50): 2^50 ≈ 10^15 operations (impossible!)
     * 
     * Space Complexity: O(n)
     * WHY? Recursion stack depth is n (fib(n) → fib(n-1) → ... → fib(1))
     * 
     * WITH MEMOIZATION (fibWithMemoization):
     * --------------------------------------
     * Time Complexity: O(n) - linear
     * 
     * WHY LINEAR?
     * Memoization caches results, so each fib(k) is computed ONLY ONCE.
     * 
     * Example with memo:
     * fib(5) - memo: {}
     * ├── fib(4) - memo: {}
     * │   ├── fib(3) - memo: {}
     * │   │   ├── fib(2) = 1 → memo[2] = 1
     * │   │   └── fib(1) = 1 → memo[1] = 1
     * │   │   └── memo[3] = fib(2) + fib(1) = 2
     * │   └── fib(2) ✅ CACHE HIT! Returns 1 immediately
     * │   └── memo[4] = fib(3) + fib(2) = 3
     * └── fib(3) ✅ CACHE HIT! Returns 2 immediately
     * └── memo[5] = fib(4) + fib(3) = 5
     * 
     * Key insight: Once fib(k) is computed, we never recompute it.
     * 
     * Calculation:
     * - Unique subproblems: n (values 1, 2, 3, ..., n)
     * - Work per subproblem: O(1) (just addition and lookup)
     * - Total work: n × 1 = O(n)
     * 
     * For fib(50): 50 operations (instant!)
     * 
     * Space Complexity: O(n)
     * WHY?
     * - Memo map: stores n entries (one per Fibonacci number)
     * - Recursion stack: O(n) depth
     * - Total: O(n) + O(n) = O(n)
     * 
     * SUMMARY:
     * Memoization trades O(n) space for O(2^n) → O(n) time improvement.
     * Each subproblem solved exactly once instead of being recomputed.
     */

    // Naive recursive approach - NO memoization
    // Time Complexity: O(2^n) - exponential due to redundant calculations
    // Space Complexity: O(n) - recursion stack depth
    public static int fibWithoutMemoization(int n){
        // Base case: first two Fibonacci numbers are 1
        if(n <=2 ) return 1;
        // Recursive case: sum of previous two Fibonacci numbers
        // This causes exponential blow-up as same subproblems are recomputed
        return fibWithoutMemoization(n-1) + fibWithoutMemoization(n-2);
    }

    // Wrapper function to initialize empty memo map
    // Returns long to handle large Fibonacci numbers (fib(50) is huge)
    private static long fib(int n){
        Map<Integer,Long> memo = new HashMap<>();
        return fibWithMemoization(n, memo);
    }

    // Optimized recursive approach - WITH memoization
    // memo stores: n -> fib(n) (the nth Fibonacci number)
    // Time Complexity: O(n) - each Fibonacci number computed only once
    // Space Complexity: O(n) for memo map + O(n) for recursion stack = O(n)
    public static long fibWithMemoization(int n,Map<Integer,Long> memo){
        // Check if we already computed this Fibonacci number (cache hit)
        if(memo.containsKey(n)) { return memo.get(n); }
        // Base case: first two Fibonacci numbers are 1
        if(n <=2 ) return 1;
        // Compute and cache the result: fib(n) = fib(n-1) + fib(n-2)
        memo.put(n, fibWithMemoization(n-1,memo) + fibWithMemoization(n-2,memo));
        return memo.get(n);
    }

    public static void main(String[] args) {
        // Test cases WITHOUT memoization (slow for large n)
//        System.out.println(fibWithoutMemoization(5));   // Output: 5
//        System.out.println(fibWithoutMemoization(6));   // Output: 8
//        System.out.println(fibWithoutMemoization(8));   // Output: 21
//        System.out.println(fibWithoutMemoization(50));  // Very slow/exponential time
        
        // Test cases WITH memoization (fast even for large n)
        System.out.println(fib(5));   // Output: 5
        System.out.println(fib(6));   // Output: 8
        System.out.println(fib(8));   // Output: 21
        System.out.println(fib(50));  // Output: 12586269025 (computed instantly)

    }




}

package ds.dynamicprogramming.memoization;

import java.util.HashMap;
import java.util.Map;

public class CanSumProblem {

    /*
     * COMPLEXITY ANALYSIS EXPLAINED IN DETAIL
     * ========================================
     * 
     * PROBLEM: Can we sum numbers from array to reach targetSum?
     * 
     * WITHOUT MEMOIZATION (canSumWithoutMemoization):
     * ------------------------------------------------
     * Time Complexity: O(n^m) where n = array length, m = targetSum/min(numbers)
     * 
     * WHY EXPONENTIAL?
     * Example: targetSum=7, numbers=[2,3]
     * 
     * Recursion tree:
     * canSum(7)
     * ├── canSum(5) [7-2]
     * │   ├── canSum(3) [5-2]
     * │   │   ├── canSum(1) [3-2]
     * │   │   │   ├── canSum(-1) ❌
     * │   │   │   └── canSum(-2) ❌
     * │   │   └── canSum(0) [3-3] ✅
     * │   └── canSum(2) [5-3]
     * │       ├── canSum(0) ✅
     * │       └── canSum(-1) ❌
     * └── canSum(4) [7-3]
     *     ├── canSum(2) [REPEATED!]
     *     └── canSum(1) [REPEATED!]
     * 
     * Branching factor = n (each call splits into n new calls)
     * Tree height = m (max depth before reaching 0)
     * Total nodes = n^m (exponential growth)
     * 
     * THE PROBLEM: Same subproblems (like canSum(2)) are computed multiple times!
     * For targetSum=779, numbers=[7,14]: 2^111 ≈ 10^33 operations (impossible)
     * 
     * Space Complexity: O(m)
     * WHY? Only one path from root to leaf is on the stack at any time.
     * Deepest path: keep subtracting smallest number until reaching 0.
     * Depth ≈ targetSum/min(numbers) = O(m)
     * 
     * WITH MEMOIZATION (canSumWithMemoization):
     * -----------------------------------------
     * Time Complexity: O(n × m) where n = array length, m = targetSum
     * 
     * WHY POLYNOMIAL?
     * Memoization caches results, so each unique sum is computed ONLY ONCE.
     * 
     * Example with memo:
     * canSum(7) - memo: {}
     * ├── canSum(5) - memo: {}
     * │   ├── canSum(3) - memo: {}
     * │   │   └── canSum(0) ✅ → memo[3] = true
     * │   └── canSum(2) - memo: {3: true}
     * │       └── canSum(0) ✅ → memo[2] = true
     * └── canSum(4) - memo: {2: true, 3: true}
     *     ├── canSum(2) ✅ CACHE HIT! (returns true immediately)
     *     └── canSum(1) ✅ CACHE HIT! (returns false immediately)
     * 
     * Key insight: Once canSum(k) is computed, we never recompute it.
     * 
     * Calculation:
     * - Unique subproblems: m (values 0, 1, 2, ..., targetSum)
     * - Work per subproblem: n (loop through array)
     * - Total work: m × n
     * 
     * For targetSum=779, numbers=[7,14]: 779 × 2 = 1,558 operations (instant!)
     * 
     * Space Complexity: O(m)
     * WHY? 
     * - Memo map: stores at most m entries (one per possible sum)
     * - Recursion stack: O(m) depth
     * - Total: O(m) + O(m) = O(m)
     * 
     * SUMMARY:
     * Memoization trades O(m) space for O(n^m) → O(n×m) time improvement.
     * Each subproblem solved exactly once instead of being recomputed.
     */

    // Naive recursive approach - NO memoization
    // Problem: Can we sum numbers from array to reach targetSum?
    // Time Complexity: O(n^m) where n = array length, m = targetSum/min(numbers)
    // Space Complexity: O(m) for recursion stack depth
    public static boolean canSumWithoutMemoization(int targetSum, int[] numbers) {
         // Base case 1: If targetSum becomes 0, we found a valid combination
         if(targetSum == 0) return true;
         // Base case 2: If targetSum becomes negative, this path is invalid
         if(targetSum < 0) return false;
         // Try subtracting each number from the array
         for(int num : numbers){
             int remainder = targetSum - num;  // What's left after using this number
             // Recursively check if remainder can be formed
             if(canSumWithoutMemoization(remainder, numbers)){
                 return true;  // Found a valid path, return immediately
             }
         }
         // Tried all numbers, none worked
         return false;
    }

    // Optimized recursive approach - WITH memoization
    // memo stores: targetSum -> can it be formed? (true/false)
    // Time Complexity: O(n × m) where n = array length, m = targetSum
    // Space Complexity: O(m) for memo map + O(m) for recursion stack = O(m)
    public  static boolean canSumWithMemoization(int targetSum, int[] numbers, Map<Integer, Boolean> memo) {
        // Check if we already computed this targetSum before (cache hit)
        if(memo.containsKey(targetSum)) { return  memo.get(targetSum); }
        // Base case 1: Successfully reached 0
        if(targetSum == 0) return true;
        // Base case 2: Went negative, invalid path
        if(targetSum < 0) return false;
        // Try each number in the array
        for(int num : numbers){
            int remainder = targetSum - num ;  // Remaining sum after using this number
            // Recursively check if remainder can be formed
            if(canSumWithMemoization(remainder, numbers, memo)){
                memo.put(targetSum, true);  // Cache the result: this targetSum IS achievable
                return true;
            }
        }
        // No number worked - this targetSum CANNOT be formed
        memo.put(targetSum, false);  // Cache the result
        return false;
    }

    // Wrapper function to initialize empty memo map
    public static boolean canSumWithMemoizationWrapper(int targetSum, int[] numbers) {
        Map<Integer, Boolean> memo = new HashMap<>();
        return canSumWithMemoization(targetSum, numbers, memo);
    }

    public static void main(String[] args) {
//        System.out.println(canSumWithoutMemoization(7, new int[] {2, 3}));
//        System.out.println(canSumWithoutMemoization(7, new int[] {5, 3, 4, 7}));
//        System.out.println(canSumWithoutMemoization(7, new int[] {2, 4}));
//        System.out.println(canSumWithoutMemoization(8, new int[] {2,3,5}));
//        System.out.println(canSumWithoutMemoization(779, new int[] {7, 14}));

        // Test cases for memoized approach (fast even for large inputs)
        System.out.println(canSumWithMemoizationWrapper(7, new int[] {2, 3}));      // true: 2+2+3
        System.out.println(canSumWithMemoizationWrapper(7, new int[] {5, 3, 4, 7})); // true: 7 or 3+4
        System.out.println(canSumWithMemoizationWrapper(7, new int[] {2, 4}));       // false: can't make 7 with evens
        System.out.println(canSumWithMemoizationWrapper(8, new int[] {2,3,5}));      // true: 3+5 or 2+2+2+2
        System.out.println(canSumWithMemoizationWrapper(779, new int[] {7, 14}));   // true: many 7s and 14s
    }
}

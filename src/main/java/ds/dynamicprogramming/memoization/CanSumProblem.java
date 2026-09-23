package ds.dynamicprogramming.memoization;

import java.util.HashMap;
import java.util.Map;

public class CanSumProblem {

    /*
     * Time Complexity:
     * - Without memoization: O(n^m) where n = array length, m = targetSum
     * - With memoization: O(n × m) where n = array length, m = targetSum
     * 
     * Space Complexity: O(m) for recursion stack + memo map
     */

    // Time: O(n^m) | Space: O(m)
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

    // Time: O(n × m) | Space: O(m)
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

        // true: 2+2+3
        System.out.println(canSumWithMemoizationWrapper(7, new int[] {2, 3}));

        // true: 7 or 3+4
        System.out.println(canSumWithMemoizationWrapper(7, new int[] {5, 3, 4, 7}));

        // false: can't make 7 with evens
        System.out.println(canSumWithMemoizationWrapper(7, new int[] {2, 4}));

        // true: 3+5 or 2+2+2+2
        System.out.println(canSumWithMemoizationWrapper(8, new int[] {2,3,5}));

        // true: many 7s and 14s
        System.out.println(canSumWithMemoizationWrapper(779, new int[] {7, 14}));
    }
}

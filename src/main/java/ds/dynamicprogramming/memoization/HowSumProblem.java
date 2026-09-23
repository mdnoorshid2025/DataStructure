package ds.dynamicprogramming.memoization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HowSum Problem - Dynamic Programming
 * Problem: Given a target sum and an array of numbers, return an array containing
 * any combination of numbers that add up to the target sum. If no combination exists,
 * return null.
 * 
 * Time Complexity:
 * - Without memoization: O(n^m * m) where n = array length, m = target sum
 * - With memoization: O(n × m × m) where n = array length, m = target sum
 * 
 * Space Complexity:
 * - Without memoization: O( m) where n = array length, m = target sum
 * - With memoization: O( m × m) where n = array length, m = target sum
 */
public class HowSumProblem {

    /**
     * Finds a combination of numbers that sum to the target using recursion (brute force)
     * Time: O(n^m * m) | Space: O(m)
     * 
     * @param targetSum The desired sum to achieve
     * @param numbers Array of numbers that can be used to sum to target
     * @return List of numbers that sum to target, or null if impossible
     */
    public static List<Integer> howSumWithoutMemoization(int targetSum, int[] numbers) {
        // Base case 1: Target sum reached - return empty list (valid combination found)
        if (targetSum == 0) {
            return new ArrayList<>();
        }
        // Base case 2: Target sum went negative - invalid path, return null
        if (targetSum < 0) {
            return null;
        }

        // Try each number in the array
        for (int num : numbers) {
            // Calculate remaining sum after using current number
            int reminder = targetSum - num;
            
            // Recursively try to find combination for the remainder
            List<Integer> result = howSumWithoutMemoization(reminder, numbers);
            
            // If recursive call found a valid combination, add current number and return
            if (result != null) {
                result.add(num);
                return result;
            }
        }
        // No valid combination found after trying all numbers
        return null;
    }

    // Time: O(n × m × m) | Space: O(m x m)
    public static List<Integer> howSumWithMemoization(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo) {
       if(targetSum == 0) return new ArrayList<>();

       if(targetSum < 0) return null;

       if(memo.containsKey(targetSum)) return memo.get(targetSum);

       for(int num : numbers){
           int remainder = targetSum - num;
           List<Integer> result = howSumWithMemoization(remainder, numbers, memo);
           if(result != null){
               result.add(num);
               memo.put(targetSum, result);
               return result;
           }
       }
        memo.put(targetSum, null);
        return null;
    }

    public static List<Integer> howSumWithMemoizationExecute(int targetSum, int[] numbers) {
        Map<Integer, List<Integer>> memo = new HashMap<>();
        return howSumWithMemoization(targetSum, numbers, memo);
    }

//    public static void main(String[] args) {
//        // [3, 2, 2]
//        System.out.println(howSumWithoutMemoization(7, new int[]{2, 3}));
//
//        // [7]
//        System.out.println(howSumWithoutMemoization(7, new int[]{5, 3, 4, 7}));
//
//        // null
//        System.out.println(howSumWithoutMemoization(7, new int[]{2, 4}));
//
//        // [3, 5]
//        System.out.println(howSumWithoutMemoization(8, new int[]{2, 3, 5}));
//
//        // null
//        System.out.println(howSumWithoutMemoization(300, new int[]{7, 14}));
//    }
public static void main(String[] args) {
    // [3, 2, 2]
    System.out.println(howSumWithMemoizationExecute(7, new int[]{2, 3}));

    // [7]
    System.out.println(howSumWithMemoizationExecute(7, new int[]{5, 3, 4, 7}));

    // null
    System.out.println(howSumWithMemoizationExecute(7, new int[]{2, 4}));

    // [3, 5]
    System.out.println(howSumWithMemoizationExecute(8, new int[]{2, 3, 5}));

    // null
    System.out.println(howSumWithMemoizationExecute(300, new int[]{7, 14}));
}
}

package ds.dynamicprogramming.memoization;

import java.util.ArrayList;
import java.util.List;

/**
 * HowSum Problem - Dynamic Programming
 * Problem: Given a target sum and an array of numbers, return an array containing
 * any combination of numbers that add up to the target sum. If no combination exists,
 * return null.
 * 
 * Time Complexity: O(n^m * m) where n = array length, m = target sum
 * Space Complexity: O(m) for recursion stack
 */
public class HowSumProblem {

    /**
     * Finds a combination of numbers that sum to the target using recursion (brute force)
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
            
            // If remainder is 0, we found a valid combination with just this number
            if (reminder == 0) {
                return new ArrayList<>(List.of(num));
            }
            
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

    public static void main(String[] args) {
        // Test case 1: target=7, numbers=[2,3] -> Expected: [3,2,2] or similar
        System.out.println(howSumWithoutMemoization(7, new int[]{2, 3}));
        
        // Test case 2: target=7, numbers=[5,3,4,7] -> Expected: [7] or [3,4]
        System.out.println(howSumWithoutMemoization(7, new int[]{5, 3, 4, 7}));
        
        // Test case 3: target=7, numbers=[2,4] -> Expected: null (impossible)
        System.out.println(howSumWithoutMemoization(7, new int[]{2, 4}));
        
        // Test case 4: target=300, numbers=[7,14] -> Expected: null (will be slow without memoization)
        System.out.println(howSumWithoutMemoization(300, new int[]{7, 14}));
    }
}

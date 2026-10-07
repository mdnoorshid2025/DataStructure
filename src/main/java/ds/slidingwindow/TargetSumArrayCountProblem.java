package ds.slidingwindow;

/**
 * Problem: Count the number of contiguous subarrays of size k that sum to target.
 * Given an array of integers, a target sum, and window size k, return the count
 * of subarrays with exactly k elements whose sum equals the target.
 */
public class TargetSumArrayCountProblem {

    public static int targetSumArrayCount(int[] nums, int target,int k) {
        int currentSum = 0; // Initialize sum for current window
        int count = 0 ; // Initialize count of matching windows
       for(int i = 0 ; i < k ; i++){ // Calculate sum of first window
           currentSum += nums[i]; // Add each element to current sum
           if(currentSum == target){ // Check if window sum matches target
               count++; // Increment count if match found
           }
       }
       for(int i = 0 ; i < nums.length -k ;i++){ // Slide window across array
           currentSum -= nums[i]; // Remove leftmost element from window
           currentSum += nums[i+k]; // Add new rightmost element to window
           if(currentSum == target){ // Check if window sum matches target
               count++; // Increment count if match found
           }
       }
       return count; // Return total count of matching windows
    }

    public static void main(String[] args) {
    System.out.println(targetSumArrayCount(new int[] {2, 3, 2, 2, 3, 1, 3, 8, 5, 0, 2, 4}, 7,3)); // Test with sample array 1
    System.out.println(targetSumArrayCount(new int[] {1, 2, 2, 2, 2, 4, 6, 5, 1, 2, 0, 10, -2, 7}, 8,4)); // Test with sample array 2

    }

    // Time Complexity: O(n) - Single pass through array
    // Space Complexity: O(1) - Constant extra space used
}

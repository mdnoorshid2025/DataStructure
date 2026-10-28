package ds.slidingwindow;

/**
 * Problem Statement:
 * Given an array of positive integers and a target sum, find the length of the longest
 * contiguous subarray whose sum equals the target sum. If no such subarray exists, return -1.
 *
 * Example:
 * Input: nums = [4, 2, 2, 2, 1, 1], targetSum = 6
 * Output: 4 (subarray [2, 2, 2] has sum 6, but [2, 2, 1, 1] has sum 6 with length 4)
 *
 * Approach: Sliding Window Technique
 * - Maintain a window defined by start and end pointers
 * - Expand the window by adding elements from the end
 * - Shrink the window from the start when the sum exceeds the target
 * - Track the maximum window length when sum equals target
 */
public class LongestSubArraySumProblem {
    public static int longestSubArraySum(int[] nums, int targetSum) {
        int start = 0;           // Left pointer of the sliding window
        int longest = -1;         // Length of the longest valid subarray found
        int windowSum = 0;        // Sum of the current window

        // Expand the window by moving the end pointer
        for(int end = 0; end < nums.length; end++){
            windowSum += nums[end];  // Add current element to window sum

            // Shrink the window from the left while sum exceeds target
            while(windowSum > targetSum){
                 windowSum -= nums[start];  // Remove leftmost element from sum
                 start++;                   // Move left pointer right
            }

            // If current window sum equals target, update longest length
            if(windowSum == targetSum){
                longest = Math.max(longest, end - start + 1);
            }

        }
        return longest;
    }

    public static void main(String[] args) {
      System.out.println(longestSubArraySum(new int[] {7, 2, 4, 2, 1}, 5)); // -1
      System.out.println(longestSubArraySum(new int[] {4, 2, 2, 2, 1, 1}, 6)); // 4
      System.out.println(longestSubArraySum(new int[] {1, 5, 2, 4, 9, 2}, 11)); // 3
      System.out.println(longestSubArraySum(new int[] {10, 4, 8, 4}, 8)); // 1
      System.out.println(longestSubArraySum(new int[] {10, 4, 8, 0, 4}, 8)); // 2
    }
}

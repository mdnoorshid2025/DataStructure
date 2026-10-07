package ds.slidingwindow;

/**
 * Problem: Find the maximum sum of any contiguous subarray of size k.
 * Given an array of integers and a window size k, return the maximum sum
 * of any subarray with exactly k elements using sliding window technique.
 */
public class MaxSubArraySumSizeProblem {

    public static int maxSubArraySum(int[] nums,int k) {
        int currentSum = 0; // Initialize sum for current window

       for(int i =0 ; i<=k-1;i++){ // Calculate sum of first window
           currentSum += nums[i]; // Add each element to current sum
       }

        int maxSum = currentSum; // Initialize max sum with first window sum

       for(int i = 0; i < nums.length-k ; i++){ // Slide window across array
           currentSum -= nums[i]; // Remove leftmost element from window
           currentSum += nums[i+k]; // Add new rightmost element to window
           if(currentSum > maxSum){ // Check if current window sum is greater
               maxSum = currentSum; // Update max sum if current is larger
           }
       }
     return maxSum; // Return maximum sum found
    }

    public static void main(String[] args) {
        System.out.println(maxSubArraySum(new int[] {4,2,1,-9,8,4,3}, 3)); // Test with sample array
        System.out.println(maxSubArraySum(new int[] {-9,1,8,2,3,7}, 3)); // Test with sample array

    }

    // Time Complexity: O(n) - Single pass through array
    // Space Complexity: O(1) - Constant extra space used
}

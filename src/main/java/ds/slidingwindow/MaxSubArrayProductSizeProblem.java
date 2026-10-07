package ds.slidingwindow;

/**
 * Problem: Find the maximum product of any contiguous subarray of size k.
 * Given an array of integers and a window size k, return the maximum product
 * of any subarray with exactly k elements using sliding window technique.
 */
public class MaxSubArrayProductSizeProblem {

    public static int maxSubArrayProduct(int[] nums, int k) {
        int currentProduct = 1; // Initialize product for current window
        for(int i = 0 ; i<k;i++){ // Calculate product of first window
            currentProduct *= nums[i]; // Multiply each element to current product
        }
        int maxProduct = currentProduct; // Initialize max product with first window product

        for(int i =0 ; i < nums.length-k ;i++){ // Slide window across array
            currentProduct /= nums[i]; // Divide by leftmost element from window
            currentProduct *= nums[i+k]; // Multiply by new rightmost element to window
            if(currentProduct > maxProduct){ // Check if current window product is greater
                maxProduct = currentProduct; // Update max product if current is larger
            }
        }
        return maxProduct; // Return maximum product found
    }

    public static void main(String[] args) {
        System.out.println(maxSubArrayProduct(new int[] {4,2,1,-9,8,2,3}, 3)); // Test with sample array
        System.out.println(maxSubArrayProduct(new int[] {-9,1,-8,2,3,7}, 3)); // Test with sample array

    }

    // Time Complexity: O(n) - Single pass through array
    // Space Complexity: O(1) - Constant extra space used
}

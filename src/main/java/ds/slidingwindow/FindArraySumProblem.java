package ds.slidingwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Find Subarray with Given Sum
 *
 * Given a list of positive integers and a target sum, find a contiguous subarray
 * that sums to the target value.
 *
 * Description: Find the start and end indices (0-based) of a contiguous subarray whose
 * elements sum exactly to the target. If multiple such subarrays exist, return the first
 * one found. If no such subarray exists, return an empty list.
 *
 * Input:
 * - nums: A list of positive integers
 * - target: The target sum to find
 *
 * Output: A list containing [start, end] indices if a subarray is found, or an empty list otherwise.
 *
 * Examples:
 * - findArraySum([1, 2, 3, 7, 5], 12) → [1, 3] (subarray [2, 3, 7])
 * - findArraySum([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 15) → [0, 4] (subarray [1, 2, 3, 4, 5])
 * - findArraySum([3, 1, 4, 9, 2, 1, 7], 10) → [4, 6] (subarray [2, 1, 7])
 *
 * Approach: Sliding window technique that expands the window by adding elements and shrinks
 * from the left when the sum exceeds the target.
 *
 * Time Complexity: O(n) - Single pass through the array
 * Space Complexity: O(1) - Constant extra space
 */
public class FindArraySumProblem {
    public static List<Integer> findArraySum(List<Integer> nums, int target) {
        int start = 0;
        int windowSum = 0;
        for (int end = 0; end < nums.size(); end++) {
            windowSum += nums.get(end);
            while (windowSum > target) {
                windowSum -= nums.get(start);
                start++;
            }
            if (windowSum == target) {
                return List.of(start, end);
            }

        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(findArraySum(List.of(1, 2, 3, 7, 5), 12)); //[1,3]
        System.out.println(findArraySum(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 15)); //[0,4]
        System.out.println(findArraySum(List.of(3, 1, 4, 9, 2, 1, 7), 10)); //[4,6]
        System.out.println(findArraySum(List.of(3, 1, 4, 9, 2, 1, 7), 11)); //[3,4]
    }
}

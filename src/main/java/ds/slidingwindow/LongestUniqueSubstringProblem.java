package ds.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Longest Substring Without Repeating Characters
 * 
 * Given a string s, find the length of the longest substring without repeating characters.
 * 
 * Examples:
 * - Input: "abcabcbb" -> Output: 3 (Explanation: "abc" is the longest substring without repeating characters)
 * - Input: "bbbbb" -> Output: 1 (Explanation: "b" is the longest substring without repeating characters)
 * - Input: "pwwkew" -> Output: 3 (Explanation: "wke" is the longest substring without repeating characters)
 * 
 * Approach: Sliding Window with HashMap
 * - Use two pointers (start and end) to maintain a window of unique characters
 * - Use a HashMap to track character frequencies within the current window
 * - Expand the window by moving the end pointer and adding characters to the map
 * - If a duplicate is found, shrink the window from the start until uniqueness is restored
 * - Track the maximum window size encountered
 * 
 * Time Complexity: O(n) - each character is visited at most twice (once by end, once by start)
 * Space Complexity: O(min(m, n)) - where m is the character set size, n is string length
 */
public class LongestUniqueSubstringProblem {

    public static int longestUniqueSubstring(String s) {
        // Start pointer of the sliding window (left boundary)
        int start = 0;
        // Track the maximum length of substring with unique characters found so far
        int longest = 0;
        // HashMap to store character frequencies within the current window
        Map<Character, Integer> windowCounter = new HashMap<>();
        
        // Expand the window by moving the end pointer (right boundary)
        for (int end = 0; end < s.length(); end++) {
            // Get the character at the current end position
            char leadingCharacter = s.charAt(end);
            // Add the character to the window counter (increment frequency)
            windowCounter.put(leadingCharacter, windowCounter.getOrDefault(leadingCharacter, 0) + 1);

            // If the current character appears more than once in the window,
            // we need to shrink the window from the start until uniqueness is restored
            while (windowCounter.get(leadingCharacter) > 1) {
                // Get the character at the current start position
                char trailingCharacter = s.charAt(start);
                // Remove it from the window counter (decrement frequency)
                windowCounter.put(trailingCharacter, windowCounter.get(trailingCharacter) - 1);
                // Move the start pointer forward to shrink the window
                start++;
            }

            // Update the maximum length if the current window is larger
            // Window size = end - start + 1 (both indices are inclusive)
            longest = Math.max(longest, end - start + 1);
        }
        return longest;
    }

    public static void main(String[] args) {
        // Test cases with expected outputs
        System.out.println(longestUniqueSubstring("abcabcqbb")); // Expected: 4 ("abcq")
        System.out.println(longestUniqueSubstring("forgeonwards")); // Expected: 9 ("geonwards")
        System.out.println(longestUniqueSubstring("tmmzuxt")); // Expected: 5 ("mzuxt")
        System.out.println(longestUniqueSubstring("racecarisgreatness")); // Expected: 8 ("isgreat")
        System.out.println(longestUniqueSubstring("zzzzzz")); // Expected: 1 ("z")
        System.out.println(longestUniqueSubstring("zqzzzz")); // Expected: 2 ("zq")
    }

}

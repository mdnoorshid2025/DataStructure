package ds.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class CountSubStringAnagramProblem {

    public static int countSubStringAnagram(String s, String anagram) {
        int k = anagram.length(); // Window size

        if (k > s.length()) {
            return 0;
        }

        // Initialize counters for both window and anagram
        Map<Character, Integer> windowCounter = new HashMap<>();
        Map<Character, Integer> anagramCounter = new HashMap<>();

        //Build frequency map for both target and first window
        for (int i = 0; i < k; i++) {
            char anagramChar = anagram.charAt(i);
            char windowChar = s.charAt(i);

            anagramCounter.put(anagramChar, anagramCounter.getOrDefault(anagramChar, 0) + 1);
            windowCounter.put(windowChar, windowCounter.getOrDefault(windowChar, 0) + 1);
        }

        // Check initial window
        int numMatches = windowCounter.equals(anagramCounter) ? 1 : 0;

        // Slide the window for further matching
        for (int i = 0; i < s.length() - k; i++) {
            // trailing character need to remove from window
            char trailingChar = s.charAt(i);
            // leading character need to add to window
            char leadingChar = s.charAt(i + k);
            // Remove trailing character from window
            windowCounter.put(trailingChar, windowCounter.get(trailingChar) - 1);

            // Remove trailing character key from window if count becomes zero
            if (windowCounter.get(trailingChar) == 0) {
                windowCounter.remove(trailingChar);
            }

            // Add leading character to window
            windowCounter.put(leadingChar, windowCounter.getOrDefault(leadingChar, 0) + 1);

            //Finally checking for anagram matching with the window and incrementing the count
            if (windowCounter.equals(anagramCounter)) {
                numMatches++;
            }

        }

        return numMatches;
    }

    public static void main(String[] args) {
        System.out.println(countSubStringAnagram("tacoctacabcatt", "cat")); //4
        System.out.println(countSubStringAnagram("qtqt", "qt")); //3
        System.out.println(countSubStringAnagram("gattactat", "att")); //3
        System.out.println(countSubStringAnagram("gattactat", "tag")); //1
        System.out.println(countSubStringAnagram("rreeadreaerrand", "reade")); //4
        System.out.println(countSubStringAnagram("versorevairlg", "serve")); //0

        // Time Complexity: O(n*k) - Single pass through string
        // Space Complexity: O(k) - Space for character sets (k = anagram length)

    }
}

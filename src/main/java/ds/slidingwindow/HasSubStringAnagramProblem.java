package ds.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Check if any substring of string s is an anagram of the given anagram string.
 * Given a string s and an anagram pattern, return true if any substring of s
 * has the same characters as the anagram using sliding window technique.
 */
public class HasSubStringAnagramProblem {

    public static boolean hasSubStringAnagram(String s, String anagram) {
        int k = anagram.length(); // Get window size from anagram length
        if(k > s.length()) return false; // Return false if anagram is longer than string

        Set<Character> windowSet = new HashSet<>(); // Initialize set for current window
        Set<Character> anagramSet = new HashSet<>(); // Initialize set for anagram characters

        for(int i = 0 ; i < k ; i++){ // Build initial window and anagram sets
            windowSet.add(s.charAt(i)); // Add first k characters from string
            anagramSet.add(anagram.charAt(i)); // Add all characters from anagram
        }

        if(windowSet.equals(anagramSet)) return true; // Check if first window matches

        for(int i =0 ; i<s.length()-k ;i++){ // Slide window across string
            windowSet.remove(s.charAt(i)); // Remove leftmost character from window
            windowSet.add(s.charAt(i+k)); // Add new rightmost character to window
            if(windowSet.equals(anagramSet)) return true; // Check if current window matches
        }


        return false; // No matching anagram found

    }

    public static void main(String[] args) {
        System.out.println(hasSubStringAnagram("greyhounds", "hoy")); // Test with sample strings // true
        System.out.println(hasSubStringAnagram("gruyheonds", "hoy")); // Test with sample strings // false
        System.out.println(hasSubStringAnagram("breakdowns", "snow")); // Test with sample strings // true
        System.out.println(hasSubStringAnagram("dermatoglyphics", "red")); // Test with sample strings // true
        System.out.println(hasSubStringAnagram("southernly", "thorny")); // Test with sample strings // false
        System.out.println(hasSubStringAnagram("southernly", "nerlysouth")); // Test with sample strings // true

    }

    // Time Complexity: O(n*k) - Single pass through string
    // Space Complexity: O(k) - Space for character sets (k = anagram length)
}

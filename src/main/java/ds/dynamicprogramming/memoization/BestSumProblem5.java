package ds.dynamicprogramming.memoization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Finds the shortest combination of numbers that sum to a target.
 * Uses recursion with and without memoization to optimize performance.
 */
public class BestSumProblem5 {

    /**
 * Finds the shortest combination without memoization.
 * Time: O(n^m * m) where n = numbers.length, m = targetSum
 * Space: O(m^2) for recursion stack and combinations
 */
    public static List<Integer> bestSumWithoutMemoization(int targetSum, int[] numbers) {
       if(targetSum == 0) return new ArrayList<>();
       if(targetSum < 0) return null;

       List<Integer> shortestCombination = null;

       for(int num : numbers){
           int remainder = targetSum - num;

           List<Integer> combination = bestSumWithoutMemoization(remainder, numbers);

           if(combination != null){
               combination.add(num);
              if(shortestCombination == null || combination.size() < shortestCombination.size() ){
                  shortestCombination = combination;
              }
           }

       }
        return shortestCombination;
    }

    /**
 * Finds the shortest combination with memoization.
 * Time: O(n * m^2) where n = numbers.length, m = targetSum
 * Space: O(m^2) for memo storage and recursion stack
 */
    public static List<Integer> bestSumWithMemoization(int targetSum, int[] numbers, Map<Integer,List<Integer>> memo) {

        if(memo.containsKey(targetSum)) return memo.get(targetSum);
        if(targetSum == 0 ) return new ArrayList<>();
        if(targetSum < 0) return null;



        List<Integer> shortestCombination = null;

        for(int num: numbers){

            int remainder = targetSum - num;

            List<Integer> remainderCombination = bestSumWithMemoization(remainder,numbers,memo);

            if(remainderCombination != null){
                List<Integer> combination = new ArrayList<>(remainderCombination);
                combination.add(num);
                if(shortestCombination == null || combination.size() < shortestCombination.size() ){
                    shortestCombination = combination;
                }
            }
        }

        memo.put(targetSum,shortestCombination);
        return shortestCombination;
    }

    /**
 * Wrapper method to execute memoized version.
 * Time: O(n * m^2) where n = numbers.length, m = targetSum
 * Space: O(m^2) for memo storage and recursion stack
 */
    public static List<Integer> executeBestSumWithMemoization(int targetSum, int[] numbers){
        Map<Integer,List<Integer>> memo = new HashMap<>();
        return bestSumWithMemoization(targetSum, numbers, memo);
    }

//    public static void main(String[] args) {
//        // [7]
//         bh bg bnvc
//
//        // [3, 5]
//        System.out.println(bestSumWithoutMemoization(8, new int[]{2, 3, 5}));
//
//        // [4, 4]
//        System.out.println(bestSumWithoutMemoization(8, new int[]{1, 4, 5}));
//
//        // [25, 25, 25, 25]
//        System.out.println(bestSumWithoutMemoization(100, new int[]{1, 2, 5, 25}));
//    }

    public static void main(String[] args) {
        // [7]
        System.out.println(executeBestSumWithMemoization(7, new int[]{5, 3, 4, 7}));

        // [3, 5]
        System.out.println(executeBestSumWithMemoization(8, new int[]{2, 3, 5}));

        // [4, 4]
        System.out.println(executeBestSumWithMemoization(8, new int[]{1, 4, 5}));

        // [25, 25, 25, 25]
        System.out.println(executeBestSumWithMemoization(100, new int[]{1, 2, 5, 25}));
    }


}

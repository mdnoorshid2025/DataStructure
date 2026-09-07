package ds.dynamicprogramming.memoization;

import java.util.HashMap;
import java.util.Map;

public class Fibnocci {

    //without memoization
    public static int fibWithoutMemoization(int n){
        if(n <=2 ) return 1;
        return fibWithoutMemoization(n-1) + fibWithoutMemoization(n-2);
    }

    private static long fib(int n){
        Map<Integer,Long> memo = new HashMap<>();
        return fibWithMemoization(n, memo);
    }

    //without memoization
    public static long fibWithMemoization(int n,Map<Integer,Long> memo){
        if(memo.containsKey(n)) { return memo.get(n); }
        if(n <=2 ) return 1;
        memo.put(n, fibWithMemoization(n-1,memo) + fibWithMemoization(n-2,memo));
        return memo.get(n);
    }

    public static void main(String[] args) {
//        System.out.println(fibWithoutMemoization(5));
//        System.out.println(fibWithoutMemoization(6));
//        System.out.println(fibWithoutMemoization(8));
//        System.out.println(fibWithoutMemoization(50));
        System.out.println(fib(5));
        System.out.println(fib(6));
        System.out.println(fib(8));
        System.out.println(fib(50));

    }




}

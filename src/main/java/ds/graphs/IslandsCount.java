package ds.graphs;

import java.util.HashSet;
import java.util.Set;

/**
 * Counts the number of islands in a 2D grid.
 * An island is a group of adjacent 'L' (land) cells connected horizontally or vertically.
 * 'W' (water) cells are not part of any island.
 * 
 * This is essentially a connected components problem on a 2D grid using DFS traversal.
 * Each island represents one connected component of land cells.
 * 
 * Time Complexity: O(R * C) where R = number of rows, C = number of columns
 * Space Complexity: O(R * C) for the visited set in worst case
 */
public class IslandsCount {
    /**
     * Counts the number of islands in the grid.
     * Iterates through each cell and uses DFS to explore each island.
     * 
     * Time Complexity: O(R * C) - visits each cell once
     * Space Complexity: O(R * C) for the visited set in worst case
     * 
     * @param grid 2D array where 'L' = land, 'W' = water
     * @return Number of islands (connected land components)
     */
    public static int countIslands(char[][] grid) {
        int count = 0; // Island counter
        Set<String> visited = new HashSet<>(); // Track visited positions to avoid cycles
        
        // Iterate through each cell in the grid
        for(int r = 0 ; r < grid.length; r++){
           for(int c = 0 ; c < grid[0].length; c++){
               // If explore returns true, we found a new unvisited island
               if(explore(grid, r, c, visited)){
                   count++;
               }
           }
       }
        return count;
    }

    /**
     * Recursively explores all cells in the current island using DFS.
     * Checks bounds, water cells, and visited status before exploring.
     * 
     * Time Complexity: O(R * C) for the entire grid traversal
     * Space Complexity: O(R * C) for the recursion stack in worst case
     * 
     * @param grid 2D array where 'L' = land, 'W' = water
     * @param r Current row position
     * @param c Current column position
     * @param visited Set of already visited positions
     * @return true if this is a new unvisited island, false otherwise
     */
    public static boolean explore(char[][] grid, int r, int c , Set<String> visited) {
     // Check if current position is within grid bounds
     boolean rowInbounds = 0 <= r && r < grid.length;
     boolean colInbounds = 0 <= c && c < grid[0].length;
     if(!rowInbounds || !colInbounds) return false;

     // Skip if current cell is water (not part of island)
     if(grid[r][c] == 'W') return false;

     // Create unique position identifier for visited tracking
     String position = r + "," + c;
     if(visited.contains(position)){
         return false; // Already visited, part of counted island
     }
     visited.add(position); // Mark current position as visited

     // Recursively explore all 4 adjacent cells (up, down, left, right)
     explore(grid, r - 1, c, visited); // Up
     explore(grid, r + 1, c, visited); // Down
     explore(grid, r, c - 1, visited); // Left
     explore(grid, r, c + 1, visited); // Right

     return true; // Completed exploring a new island
    }

    public static void main(String[] args) {
        // Test grid with 3 islands:
        // Island 1: L at (0,1) and (1,1) - vertical pair
        // Island 2: L cluster at (2,3), (3,2), (3,3), (4,3), (4,4)
        // Island 3: L cluster at (4,0), (5,0), (5,1)
        char[][] grid = {
                {'W', 'L', 'W', 'W', 'W'},
                {'W', 'L', 'W', 'W', 'W'},
                {'W', 'W', 'W', 'L', 'W'},
                {'W', 'W', 'L', 'L', 'W'},
                {'L', 'W', 'W', 'L', 'L'},
                {'L', 'L', 'W', 'W', 'W'}};
        
        // Expected output: 3 (three separate islands)
        System.out.println(countIslands(grid));
    }
}

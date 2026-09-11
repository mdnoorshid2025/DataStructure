package ds.graphs;

import java.util.HashSet;
import java.util.Set;

/**
 * Finds the smallest island size in a 2D grid.
 * An island is a group of adjacent 'L' (land) cells connected horizontally or vertically.
 * 'W' (water) cells are not part of any island.
 * 
 * COMPLEXITY ANALYSIS EXPLAINED IN DETAIL
 * ========================================
 * 
 * PROBLEM: Find the size of the smallest connected group of land cells.
 * 
 * Time Complexity: O(R * C) where R = number of rows, C = number of columns
 * 
 * WHY O(R * C)?
 * - We iterate through every cell in the grid once (R × C cells)
 * - For each land cell, we do DFS to count the island size
 * - Each cell is visited at most twice:
 *   1. Once in the outer loop
 *   2. Once during DFS (if it's land)
 * - Total work: R × C (all cells)
 * 
 * Example: 6x5 grid (30 cells)
 * - We check each of the 30 cells once
 * - DFS visits land cells, but each cell is marked visited
 * - Total operations: 30 (constant factor for neighbor checks)
 * 
 * Space Complexity: O(R * C) for the visited set in worst case
 * 
 * WHY O(R * C)?
 * - Visited set: stores at most R × C entries (one per cell)
 * - Recursion stack: in worst case (all land), depth = R × C
 * - Total: O(R × C) + O(R × C) = O(R × C)
 * 
 * ALGORITHM EXPLANATION:
 * 1. Iterate through each cell in the grid
 * 2. When we find an unvisited land cell, start DFS to count island size
 * 3. DFS returns the size of the current island
 * 4. Track the minimum size found across all islands
 * 5. Visited cells are skipped in subsequent iterations
 */
public class MinimumIsland {
    /**
     * Finds the smallest island size in the grid.
     * Iterates through each cell and uses DFS to explore each island's size.
     * 
     * Time Complexity: O(R * C) - visits each cell once
     * Space Complexity: O(R * C) for the visited set in worst case
     * 
     * @param grid 2D array where 'L' = land, 'W' = water
     * @return Size of the smallest island, or MAX_VALUE if no island exists
     */
    public static int minimumIsland(char[][] grid) {
        // Start with the largest possible value as our minimum
        int minSizeOfIsland = Integer.MAX_VALUE;
        Set<String> visited = new HashSet<>(); // Keep track of cells we've already checked
        
        // Go through every cell in the grid row by row
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                // Explore this cell to see if it's part of an island and get its size
                int sizeOfIsland = exploreSize(grid, r, c, visited);
                
                // Only update if we found a real island (size > 0) and it's smaller than current minimum
                if(sizeOfIsland > 0 && sizeOfIsland < minSizeOfIsland){
                    minSizeOfIsland = sizeOfIsland;
                }
            }
        }

        return minSizeOfIsland;
    }
    /**
     * Recursively explores all cells in the current island and counts its size.
     * Uses DFS (Depth-First Search) to traverse the island.
     * 
     * Time Complexity: O(R * C) for the entire grid traversal
     * Space Complexity: O(R * C) for the recursion stack in worst case
     * 
     * @param grid 2D array where 'L' = land, 'W' = water
     * @param r Current row position
     * @param c Current column position
     * @param visited Set of already visited positions
     * @return Size of the island (number of land cells), or 0 if not part of island
     */
    public static int exploreSize(char[][] grid, int r, int c, Set<String> visited) {
        // Start counting from 1 (the current cell itself)
        int size = 1;
        
        // Check if the current position is within the grid boundaries
        boolean rowBounds = 0 <= r && r < grid.length;
        boolean colBounds = 0 <= c && c < grid[0].length;
        if(!rowBounds || !colBounds) return 0; // Out of bounds - not part of island
        
        // If this cell is water, it's not part of an island
        if(grid[r][c] == 'W') return 0;
        
        // Create a unique identifier for this position (row,column)
        String position = r + "," + c;
        if(visited.contains(position)) return 0; // Already checked this cell
        visited.add(position); // Mark this cell as visited

        // Explore all 4 neighboring cells (up, down, left, right) and add their sizes
        size += exploreSize(grid, r - 1, c, visited); // Check cell above
        size += exploreSize(grid, r + 1, c, visited); // Check cell below
        size += exploreSize(grid, r, c - 1, visited); // Check cell to the left
        size += exploreSize(grid, r, c + 1, visited); // Check cell to the right
        
        return size; // Return total size of this island
    }

    public static void main(String[] args) {
        // Test grid with 3 islands of different sizes:
        // Island 1: 2 cells - L at (0,1) and (1,1) - vertical pair
        // Island 2: 5 cells - L cluster at (2,3), (3,2), (3,3), (4,3), (4,4)
        // Island 3: 3 cells - L cluster at (4,0), (5,0), (5,1)
        // Expected output: 2 (smallest island size)
        char[][] grid = {
                {'W', 'L', 'W', 'W', 'W'},
                {'W', 'L', 'W', 'W', 'W'},
                {'W', 'W', 'W', 'L', 'W'},
                {'W', 'W', 'L', 'L', 'W'},
                {'L', 'W', 'W', 'L', 'L'},
                {'L', 'L', 'W', 'W', 'W'}};
        
        System.out.println(minimumIsland(grid));
    }
}

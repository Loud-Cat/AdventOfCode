
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/* Advent of Code
 * Day 8: Treetop Tree House */

public class Day8 {
    public static boolean isVisible(int[] array, int index) {
        /* Returns if a given tree is visible or not at the given row/column */
        
        int value = array[index];
        
        boolean left = true;
        for (int i = 0; i < index; i++)
            if (array[i] >= array[index])
                left = false;
        
        boolean right = true;
        for (int i = index + 1; i < array.length; i++)
            if (array[i] >= array[index])
                right = false;
        
        return left || right;
    }
    
    public static int[] viewDist(int[] array, int index) {
        /* Returns the given tree's left & right viewing distance
         * at the given row/column. */
         
         int left = 0, right = 0;
         
         for (int i = index - 1; i >= 0; i--) {
             left += 1;
             if (array[i] >= array[index])
                break;
         }
         
         for (int i = index + 1; i < array.length; i++) {
             right += 1;
             if (array[i] >= array[index])
                break;
         }
         
         return new int[] {left, right};
    }
    
    public static int[][] getGrid() throws IOException {
        /* Reads the file and returns its contents as a 2D array. */
        
        BufferedReader reader = new BufferedReader( new FileReader("input.txt") );
        
        return reader.lines()
            .map(s -> s.chars().map(i -> i - '0').toArray())
            .toArray(int[][]::new);
    }
    
    public static int[][] inverseGrid(int[][] grid) {
        /* Returns a rotated view of the grid
         * where columns and rows are swapped. */
        
        int[][] cols = new int[grid[0].length][grid.length];
        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[r].length; c++)
                cols[r][c] = grid[c][r];
        
        return cols;
    }
    
    public static int partOne(int[][] rows) {
        /* The first part of the puzzle. */
        
        int[][] cols = inverseGrid(rows);
        
        int out = (2 * rows.length) + (2 * rows[0].length) - 4;
        for (int r = 1; r < rows.length - 1; r++)
            for (int c = 1; c < rows[r].length - 1; c++) {
                if (isVisible(rows[r], c) || isVisible(cols[c], r))
                    out++;
            }
        
        return out;
    }
    
    public static int partTwo(int[][] rows) {
        /* The second aprt of the puzzle. */
        
        int[][] cols = inverseGrid(rows);
        
        int out = 0;
        for (int r = 1; r < rows.length - 1; r++)
            for (int c = 1; c < rows[r].length - 1; c++) {
                int[] row = viewDist(rows[r], c);
                int[] col = viewDist(cols[c], r);
                
                out = Math.max(out, row[0] * row[1] * col[0] * col[1]);
            }
        
        return out;
    }
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        int[][] rows = getGrid();
        int one = partOne(rows);
        int two = partTwo(rows);
        long time = System.currentTimeMillis() - start;
        
        System.out.println("Part 1: " + one);
        System.out.println("Part 2: " + two);
        System.out.printf("Completed in %d ms%n", time);
    }
}

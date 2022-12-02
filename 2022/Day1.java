import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/* Advent of Code
 * Day 1 - Calorie Counting */

public class Day1 {
    private static List<Integer> solution = null;
    
    public static List<Integer> getCalories(File file) throws IOException {
        /* The solution to both parts of the puzzle can be found
         * in the same pass of the file more efficiently.
         * 
         * Returns a reverse-sorted list of total calories per elf.
         * Used in the solution
        */
        
        BufferedReader reader = new BufferedReader( new FileReader(file) );
        
        final List<Integer> totals = new ArrayList<>();
        totals.add(0);
        
        int current = 0;
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            
            if ( line.isEmpty() ) {
                totals.add(current);
                current = 0;
            } else
                current += Integer.parseInt(line);
        }
        
        totals.sort( Comparator.reverseOrder() );
        return totals;
    }
    
    public static int partOne(File file) throws IOException {
        if (solution == null) solution = getCalories(file);
        return solution.get(0);
    }
    
    public static int partTwo(File file) throws IOException {
        if (solution == null) solution = getCalories(file);
        return solution.get(0) + solution.get(1) + solution.get(2);
    }
    
    public static void main(String args[]) throws IOException {
        /* Reads the Advent of Code puzzle input text file
         * and prints the solution to the day's problem. */
        
        File file = new File("Inputs" + File.separator + "input1.txt");
        
        if ( !file.exists() ) {
            System.err.println("ERROR: cannot find file!");
            System.exit(1);
        }
        
        long start = System.nanoTime();
        int one = partOne(file);
        int two = partTwo(file);
        long time = System.nanoTime() - start;
        
        System.out.println("Part one: " + one);
        System.out.println("Part two: " + + two);
        System.out.printf("Solved in %,d microseconds%n", time / 1_000);
    }
}

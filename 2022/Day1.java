import java.io.File;
import java.util.Scanner;
import java.io.IOException;

import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

/* Day 1 - Calorie Counting */

public class Day1 {
    public static int solutionOne(File file) throws IOException {
        /* The first part of the Calorie Counting problem.
         * Returns the largest total calorie count of the elves. */
        
        Scanner input = new Scanner(file);
        
        int max = Integer.MIN_VALUE;
        int current = 0;
        
        while (input.hasNext()) {
            String line = input.nextLine();
            
            if ( !line.isEmpty() )
                current += Integer.parseInt(line);
            else {
                max = Math.max(max, current);
                current = 0;
            }
        }
        
        return max;
    }
    
    public static int solutionTwo(File file) throws IOException {
        /* The second part of the Calorie Counting problem.
         * Returns the sum of the 3 largest total calories.*/
        
        Scanner input = new Scanner(file);
        
        Set<Integer> totals = new TreeSet<>( Comparator.reverseOrder() );
        int current = 0;
        
        while (input.hasNext()) {
            String line = input.nextLine();
            
            if ( !line.isEmpty() )
                current += Integer.parseInt(line);
            else {
                totals.add(current);
                current = 0;
            }
        }
        
        return (int) totals.stream()
            .limit(3L)
            .mapToInt(Integer::intValue)
            .sum();
    }
    
    public static void main(String args[]) throws IOException {
        /* Reads the Advent of Code puzzle input text file
         * and prints the solution to the day's problem. */
        
        File file = new File("input.txt");
        
        if ( !file.exists() ) {
            System.out.println("ERROR: cannot find file!");
            System.exit(1);
        }
        
        System.out.printf("Part 1: %d" + "%n", solutionOne(file));
        System.out.printf("Part 2: %d" + "%n", solutionTwo(file));
    }
}

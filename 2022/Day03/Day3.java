import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

/* Advent of Code
 * Day 3 - Rucksack Reorganization */

public class Day3 {
    public static int partOne() throws IOException {
        /* The first part of the puzzle.
         * Returns the sum of the priority values of each
         * non-unique item type per rucksack. */
        
        BufferedReader reader = new BufferedReader( new FileReader("input.txt") );
        
        int out = 0;
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            
            ArrayList<Character> left = new ArrayList<>();
            ArrayList<Character> right = new ArrayList<>();
            int half = line.length() / 2;
            char common = 0;
            
            for (char c : line.substring(0, half).toCharArray())
                left.add(c);
            
            for (char c : line.substring(half).toCharArray()) {
                right.add(c);
                if ( left.contains(c) ) { common = c; break; }
            }
            
            out += Character.isUpperCase(common) ? common - 38 : common - 96;
        }
        
        return out;
    }
    
    public static int partTwo() throws IOException {
        /* The second part of the puzzle.
         * Returns the sum of every 3 sack's item type
         * by its priority value. */
        
        BufferedReader reader = new BufferedReader( new FileReader("input.txt") );
        
        int out = 0;
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            
            List<String> all = new ArrayList<>( List.of(line.split("")) );
            all.retainAll( List.of(reader.readLine().split("")) );
            all.retainAll( List.of(reader.readLine().split("")) );
            char common = all.get(0).charAt(0);
            
            out += Character.isUpperCase(common) ? common - 38 : common - 96;
        }
        
        return out;
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("Parts 1 and 2:");
        long start = System.currentTimeMillis();
        System.out.println( partOne() );
        System.out.println( partTwo() );
        long time = System.currentTimeMillis() - start;
        System.out.printf("Solved in %d ms%n", time);
    }
}

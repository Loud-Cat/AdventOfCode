package com.mycompany.adventofcode;

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
    public static int[] bothParts(File file) throws IOException {
        /* The solution to both parts of the puzzle can be found
         * in the same pass of the file more efficiently.
         * 
         * Returns:
           1. the total calories of the largest total per elf
           2. the sum of the first 3 largest calorie totals
        */
        
        BufferedReader reader = new BufferedReader( new FileReader(file) );
        
        final List<Integer> totals = new ArrayList<>();
        totals.add(0);
        
        reader.lines()
            .forEach((String line) -> {
                int i = totals.size() - 1;
                
                if ( !line.isEmpty() )
                    totals.set(i, totals.get(i) + Integer.parseInt(line));
                else {
                    totals.add(0);
                }
            });
        
        totals.sort( Comparator.reverseOrder() );
        
        int left = totals.get(0);
        int right = left + totals.get(1) + totals.get(2);
        return new int[] {left, right};
    }
    
    public static void main(String args[]) throws IOException {
        /* Reads the Advent of Code puzzle input text file
         * and prints the solution to the day's problem. */
        
        File file = new File("Inputs" + File.separator + "input.txt");
        
        if ( !file.exists() ) {
            System.err.println("ERROR: cannot find file!");
            return;
        }
        
        long start = System.currentTimeMillis();
        int[] solutions = bothParts(file);
        long time = System.currentTimeMillis() - start;
        
        System.out.println("Solved in " + time + " millis");
        System.out.println("Part 1: " + solutions[0]);
        System.out.println("Part 2: " + solutions[1]);
    }
}

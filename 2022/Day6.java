import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/* Advent of Code
 * Day 6 - Tuning Trouble */

public class Day6 {
    private static int getStart(int size) throws IOException {
        String file = "Inputs" + File.separator + "input6.txt";
        BufferedReader reader = new BufferedReader( new FileReader(file) );
        String stream = reader.readLine();
        
        for (int i = size; i < stream.length(); i++) {
            String[] sub = stream.substring(i-size, i).split("");
            Set<String> chars = new HashSet<>( Arrays.asList(sub) );
            if (chars.size() == size)
                return i;
        }
        
        return -1;
        
    }
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        System.out.println( getStart(4) );
        System.out.println( getStart(14) );
        long time = System.currentTimeMillis() - start;
        System.out.printf("Completed in %d ms%n", time);
    }
}

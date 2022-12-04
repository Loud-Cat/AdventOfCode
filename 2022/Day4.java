import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

/* Advent of Code
 * Day 4 - Camp Cleanup */

public class MyClass {
    public static int[] bothParts() throws IOException {
        /* Both parts of the puzzle. */
        
        String file = "Inputs" + File.separator + "input4.txt";
        BufferedReader reader = new BufferedReader( new FileReader(file) );
        
        int one = 0;
        int two = 0;
        
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            
            String[] nums = line.split("\\D");
            int a = Integer.parseInt(nums[0]);
            int b = Integer.parseInt(nums[1]);
            int c = Integer.parseInt(nums[2]);
            int d = Integer.parseInt(nums[3]);
            
            if ((a >= c && b <= d) || (c >= a && d <= b))
                one++;
            
            if ((a <= c && b >= c) || (c <= a && d >= a))
                two++;
        }
        return new int[] {one, two};
    }
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        int[] parts = bothParts();
        long time = System.currentTimeMillis() - start;
        
        System.out.println("Parts 1 and 2:");
        System.out.println(parts[0]);
        System.out.println(parts[1]);
        System.out.printf("Completed in %d ms%n", time);
    }
}

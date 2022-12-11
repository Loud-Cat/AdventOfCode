import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/* Advent of Code
 * Day 10: Cathode-Ray Tube */

public class Day10 {
    public static void solve(String file) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(file) );
        Circuit circuit = new Circuit();
        
        String line;
        while((line = reader.readLine()) != null) {
            if ( line.equals("noop") )
                circuit.next(1);
            else {
                circuit.next(2);
                circuit.add( Integer.parseInt(line.substring(5)) );
            }
        }
        
        System.out.println("Part 1: " + circuit.sum());
        System.out.println("Part 2:");
        
        for (char[] row : circuit.getScreen())
            System.out.println( new String(row) );
    }
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        solve("./input.txt");
        long time = System.currentTimeMillis() - start;
        
        System.out.printf("Solved in %d ms%n", time);
    }
}

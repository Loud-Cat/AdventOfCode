import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Set;
import java.util.HashSet;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/* Advent of Code
 * Day 9: Rope Bridge */

public class Day9 {
    public static int[] bothParts(String file) throws IOException {
        /* The second part of the puzzle */
        
        // a set to keep track of unique positions
        Set<Position> visitOne = new HashSet<>();
        Set<Position> visitTwo = new HashSet<>();
        
        // keep track of individual "tails"
        List<Node> nodes = new ArrayList<>();
        nodes.add( new Node(null) ); // head
        
        for (int i = 0; i < 9; i++)
            nodes.add( new Node(nodes.get(i)) );
        
        Node tailOne = nodes.get(1); // follows H
        Node tailTwo = nodes.get(9); // follows 8
        
        BufferedReader reader = new BufferedReader( new FileReader(file) );
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            char direction = line.charAt(0);
            int amount = Integer.parseInt( line.substring(2) );
            
            // calculate directions to move in
            int dy = (direction == 'U') ? 1 : (direction == 'D') ? -1 : 0;
            int dx = (direction == 'R') ? 1 : (direction == 'L') ? -1 : 0;
            
            // move
            for (int i = 0; i < amount; i++) {
                tailTwo.move(dx, dy);
                
                visitOne.add( new Position(tailOne.x, tailOne.y) );
                visitTwo.add( new Position(tailTwo.x, tailTwo.y) );
            }
        }
        
        return new int[] {visitOne.size(), visitTwo.size()};
    }
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        int[] solution = bothParts("input.txt");
        long time = System.currentTimeMillis() - start;
        
        System.out.println( Arrays.toString(solution) );
        System.out.printf("Completed in %d ms%n", time);
    }
}

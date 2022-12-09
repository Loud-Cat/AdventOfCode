import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Set;
import java.util.LinkedHashSet;

/* Advent of Code
 * Day 9: Rope Bridge */

public class Day9 {
    public static boolean isTouching(int tx, int ty, int hx, int hy) {
        /* Returns if the given tail is touching the given head */
        
        for (int x = tx - 1; x <= tx + 1; x++)
            for (int y = ty - 1; y <= ty + 1; y++)
                if (x == hx && y == hy)
                    return true;
        
        return false;
    }
    
    public static int partOne() throws IOException {
        /* The first part of the puzzle
         * This solution uses origin (0, 0)
         * and "up" is an increase in y. */
        
        // starting position "s"
        int headX = 0, headY = 0;
        int tailX = 0, tailY = 0;
        
        // a set to keep track of unique positions
        Set<Position> visited = new LinkedHashSet<>();
        visited.add( new Position(tailX, tailY) );
        
        BufferedReader reader = new BufferedReader( new FileReader("input.txt") );
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            char direction = line.charAt(0);
            int amount = Integer.parseInt( line.substring(2) );
            
            for (int i = 0; i < amount; i++) {
                if (direction == 'U') headY += 1;
                else if (direction == 'D') headY -= 1;
                
                else if (direction == 'L') headX -= 1;
                else if (direction == 'R') headX += 1;
                
                // calculate difference in x and y
                int dx = headX - tailX;
                int dy = headY - tailY;
                
                /* "If the head is ever two steps directly
                 * up, down, left, or right from the tail,
                 * the tail must also move one step in that direction..." */
                if (Math.abs(dx) == 2 && dy == 0)
                    tailX += (tailX < headX) ? 1 : -1;
                else if (Math.abs(dy) == 2 && dx == 0)
                    tailY += (tailY < headY) ? 1 : -1;
                
                /* "Otherwise, if the head and tail aren't touching
                 * and aren't in the same row or column,
                 * the tail always moves one step diagonally" */
                else if ( !isTouching(tailX, tailY, headX, headY) ) {
                    if (dx != 0) tailX += (tailX < headX) ? 1 : -1;
                    if (dy != 0) tailY += (tailY < headY) ? 1 : -1;
                }
                
                visited.add( new Position(tailX, tailY) );
            }
        }
        
        return visited.size();
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println( partOne() );
    }
}

class Position {
    private int x, y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position other)
            return other.getX() == x && other.getY() == y;
        
        return false;
    }
    
    @Override
    public int hashCode() { return 0; }
    
    @Override
    public String toString() { return String.format("(%d, %d)", x, y); }
}

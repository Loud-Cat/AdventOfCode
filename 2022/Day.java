import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* Advent of Code - Day xx */

public class Day {
  public static Object partOne(File file) throws IOEXception {
    /* The first part of the puzzle. */
    
    Scanner input = new Scanner(file);
    
    // more code here
    return null;
  }
  
  public static Object partTwo(File file) throws IOEXception {
    /* The second part of the puzzle. */
    
    Scanner input = new Scanner(file);
    
    // more code here
    return null;
  }
  
  public static void main(String[] args) throws IOEXception {
    /* Reads the Advent of Code puzzle input
     * and prints the solutions to the day's puzzle. */
    
    File file = new File("input.txt");
    
    if ( !input.exists() ) {
      System.out.println("ERROR: Cannot find file!");
      System.exit(1);
    }
    
    System.out.println("Advent of Code - Day xx");
    System.out.println("Part 1: " + partOne(file));
    System.out.println("Part 2: " + partTwo(file));
  }
}

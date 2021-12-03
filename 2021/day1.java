import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day1 {
  public static void main(String[] args) {
    String[] data;
    int total = 0, i = 0;

    try {
      File input = new File("Inputs/input1.txt");
      Scanner sc = new Scanner(input);

      data = new String[2000];
      while (sc.hasNextLine()) { data[i] = sc.nextLine(); i++; }
      sc.close();

      for (i = 1; i < 2000; i++) {
        int one = Integer.parseInt(data[i-1]),
        two = Integer.parseInt(data[i]);
        
        if (one < two)
          total ++;
      }
    System.out.println("Part one: " + total);

    total = 0;
    int window = Integer.parseInt(data[0]) + Integer.parseInt(data[1]) + Integer.parseInt(data[2]);
    int winTwo;
    for (i = 1; i < (2000 - 2); i++) {
      winTwo = Integer.parseInt(data[i]) + Integer.parseInt(data[i+1]) + Integer.parseInt(data[i+2]);

      if (window < winTwo)
        total ++;
      window = winTwo;
    }
    System.out.println("Part two: " + total);

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }
}

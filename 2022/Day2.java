import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* Advent of Code
 * Day 2 - Rock Paper Scissors */
 
public class Day2 {
	public static int score(char a, char b) {
		 /* Knowing  [a,b,c] => [rock, paper, scissors]
		  * Assuming [x,y,z] => [rock, paper, scissors]
		  * returns 0 for loss, 3 for tie, 6 for win
		  * where "a" is the player and "b" is the opponent*/
		
		a -= 'X';
		b -= 'A';
		
		if (a == b) return 3;
		if ((a + 2) % 3 == b) return 6;
		return 0;
	}
	
	public static char neededChoice(char a, char b) {
		/* Knowing [a,b,c] => [rock, paper, scissors]
		 * Knowing [x,y,z] => [lose, tie, win]
		 * returns the required choice to get the desired output
		 * where "a" is the opponent and "b" is the output. */
		
		if (b == 'X') return (char) ('X' + (a - 'A' + 2) % 3);
		if (b == 'Y') return (char) ('X' + a - 'A');
		return (char) ((a + 1 < 'D') ? 'X' + a + 1 - 'A' : 'X');
	}
	
	public static int partOne(File file) throws IOException {
		/* Reads throigh the file and returns the total score
		 * according to the assumed rules. */
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		return reader.lines()
		.mapToInt(s -> {
			int choice = s.charAt(2) - 'X' + 1;
			return choice + score(s.charAt(2), s.charAt(0));
		}).sum();
	}

	public static int partTwo(File file) throws IOException {
		/* Reads through the file and returns the total score
		 * according to the confirmed rules */
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		return reader.lines()
		.mapToInt(s -> {
			char choice = neededChoice(s.charAt(0), s.charAt(2));
			int c = choice - 'X' + 1;
			return c + score(choice, s.charAt(0));
		}).sum();
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File("Inputs" + File.separator + "input2.txt");
		
		if (!file.exists()) {
			System.err.println("ERROR: canot find file!");
			return;
		}
		
		long start = System.currentTimeMillis();
		int one = partOne(file);
		int two = partTwo(file);
		long time = System.currentTimeMillis() - start;
		
		System.out.println("Part 1: " + one);
		System.out.println("Part 2: " + two);
		System.out.printf("Solved in %d ms%n", time);
	}
}

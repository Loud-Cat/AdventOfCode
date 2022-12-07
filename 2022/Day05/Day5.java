import java.io.*;
import java.util.*;
import java.util.regex.MatchResult;

/* Advent of Code
 * Day 5 - Supply Stacks */

public class Day5 {
    public static String[] solve(int size) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader("input.txt") );
        
        ArrayList<ArrayDeque<String>> stacks = new ArrayList<>();
        ArrayList<ArrayList<String>> newstacks = new ArrayList<>();
        
        for (int i = 0; i < size; i++) {
            stacks.add( new ArrayDeque<String>() );
            newstacks.add( new ArrayList<String>() );
        }
        
        while (true) {
            String line = reader.readLine();
            if ( line.contains("1") ) { reader.readLine(); break; }
            
            Scanner sc = new Scanner(line);
            String[] matches = sc.findAll("(\\s{4})|(\\[\\w\\])")
                .map( result -> result.group().strip() )
                .toArray(String[]::new);
            
            for (int i = 0; i < size; i++) {
                if ( !matches[i].isEmpty() ) {
                    stacks.get(i).addFirst( matches[i].substring(1, 2) );
                    newstacks.get(i).add(0, matches[i].substring(1, 2) );
                }
            }
        }
        
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            
            String[] digits = line.split("\\D+");
            int a = Integer.parseInt(digits[1]);
            int b = Integer.parseInt(digits[2]);
            int c = Integer.parseInt(digits[3]);
            
            ArrayDeque<String> removal = stacks.get(b - 1);
            ArrayDeque<String> adding = stacks.get(c - 1);
            
            for (int i = 0; i < a; i++)
                adding.addLast( removal.pollLast() );
            
            ArrayList<String> newRemoval = newstacks.get(b - 1);
            ArrayList<String> newAdding = newstacks.get(c - 1);
            
            int rs = newRemoval.size();
            List<String> sub = newRemoval.subList(rs - a, rs);
            newAdding.addAll(sub);
            sub.clear();
        }
        
        String one = "";
        String two = "";
        
        for (ArrayDeque<String> ad : stacks)
            one += ad.peekLast();
        
        for (ArrayList<String> arr : newstacks)
            two += arr.get(arr.size() - 1);
        
        return new String[] {one, two};
    }
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        System.out.println( Arrays.toString(solve(9)) );
        long time = System.currentTimeMillis() - start;
        System.out.printf("Completed in %d ms%n", time);
    }
}

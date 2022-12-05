import java.io.*;
import java.util.*;
import java.util.regex.MatchResult;

/* Advent of Code
 * Day 5 - Supply Stacks */

public class Day5 {
    public static String solve(int size) throws IOException {
        String file = "Inputs" + File.separator + "input5.txt";
        BufferedReader reader = new BufferedReader( new FileReader(file) );
        ArrayList<ArrayDeque<String>> stacks = new ArrayList<>();
        
        for (int i = 0; i < size; i++)
            stacks.add( new ArrayDeque<String>() );
        
        while (true) {
            String line = reader.readLine();
            if ( line.contains("1") ) { reader.readLine(); break; }
            
            Scanner sc = new Scanner(line);
            String[] matches = sc.findAll("(\\s{4})|(\\[\\w\\])")
                .map( result -> result.group().strip() )
                .toArray(String[]::new);
            
            for (int i = 0; i < size; i++) {
                if ( !matches[i].isEmpty() )
                    stacks.get(i).addFirst( matches[i].substring(1, 2) );
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
        }
        
        String out = "";
        for (ArrayDeque<String> ad : stacks)
            out += ad.peekLast();
        return out;
    }
    
    public static String solveTwo(int size) throws IOException {
        String file = "Inputs" + File.separator + "input5.txt";
        BufferedReader reader = new BufferedReader( new FileReader(file) );
        ArrayList<ArrayList<String>> stacks = new ArrayList<>();
        
        for (int i = 0; i < size; i++)
            stacks.add( new ArrayList<String>() );
        
        while (true) {
            String line = reader.readLine();
            if ( line.contains("1") ) { reader.readLine(); break; }
            
            Scanner sc = new Scanner(line);
            String[] matches = sc.findAll("(\\s{4})|(\\[\\w\\])")
                .map( result -> result.group().strip() )
                .toArray(String[]::new);
            
            for (int i = 0; i < size; i++) {
                if ( !matches[i].isEmpty() )
                    stacks.get(i).add( 0, matches[i].substring(1, 2) );
            }
        }
        
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            
            String[] digits = line.split("\\D+");
            int a = Integer.parseInt(digits[1]);
            int b = Integer.parseInt(digits[2]);
            int c = Integer.parseInt(digits[3]);
            
            ArrayList<String> removal = stacks.get(b - 1);
            ArrayList<String> adding = stacks.get(c - 1);
            
            int rs = removal.size();
            List<String> sub = removal.subList(rs - a, rs);
            adding.addAll(sub);
            sub.clear();
        }
        
        String out = "";
        for (ArrayList<String> ad : stacks)
            out += ad.get(ad.size() - 1);
        return out;
    }
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        System.out.println( solve(9) );
        System.out.println( solveTwo(9) );
        long time = System.currentTimeMillis() - start;
        System.out.printf("Completed in %d ms%n", time);
    }
}

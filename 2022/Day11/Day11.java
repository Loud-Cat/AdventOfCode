import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.List;
import java.util.ArrayList;

import java.util.function.IntUnaryOperator;

public class Day11 {
    public static List<Monkey> getMonkeys(String file) throws IOException {
        List<Monkey> out = new ArrayList<>();
        
        BufferedReader reader = new BufferedReader( new FileReader(file) );
        while ( reader.ready() ) {
            reader.readLine(); // skip "Monkey x:"
            
            // "Starting items: x, x, etc"
            String itemsLine = reader.readLine();
            String[] numbers = itemsLine.replaceAll("\\D{3,}", "").split(", ");
            List<Item> items = new ArrayList<>();
            for (String n : numbers)
                items.add( new Item(Integer.parseInt(n)) );
            
            // "Operation: new = old + x"
            String opLine = reader.readLine();
            String equation = opLine.substring( opLine.indexOf("old") );
            
            IntUnaryOperator operation;
            if ( equation.matches("old . old") )
                operation = equation.contains("*") ? (i -> i * i) : (i -> i + i);
            else {
                    final int operand = Integer.parseInt( equation.substring(6) );
                    operation = equation.contains("*") ? (i -> i * operand) : (i -> i + operand);
                }
            
            // "disible by x"
            String testLine = reader.readLine();
            int test = Integer.parseInt( testLine.replaceAll("\\D", "") );
            
            // if true: x
            String trueLine = reader.readLine();
            int yes = Integer.parseInt( trueLine.replaceAll("\\D", "") );
            
            // if false: x
            String falseLine = reader.readLine();
            int no = Integer.parseInt( falseLine.replaceAll("\\D", "") );
            
            out.add( new Monkey(items, operation, test, yes, no) );
            
            // blank line
            reader.readLine();
        }
        
        return out;
    }
    
    public static void main(String[] args) throws IOException {
        List<Monkey> monkeys = getMonkeys("./input11.txt");
        Monkey.init(monkeys);
        
        int mod = monkeys.stream().map(m -> m.test).reduce(1, (a, b) -> a * b);
        
        for (int i = 0; i < 10_000; i++)
            for (Monkey monkey : monkeys)
                monkey.inspectAll(mod);
        
        monkeys.sort((a, b) -> Long.compare(b.total, a.total));
        System.out.println( monkeys.get(0).total * monkeys.get(1).total );
    }
}

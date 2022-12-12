import java.util.List;
import java.util.ArrayList;
import java.util.function.IntUnaryOperator;

public class Monkey {
    private static final List<Monkey> monkeys = new ArrayList<>();
    
    List<Item> items;
    IntUnaryOperator operation;
    int test;
    int yes, no;
    long total;
    
    public Monkey(List<Item> items, IntUnaryOperator op, int test, int yes, int no) {
        this.items = items;
        
        this.operation = op;
        
        this.test = test;
        this.yes = yes;
        this.no = no;
        
        this.total = 0;
    }
    
    public static void init(List<Monkey> monkeyz) {
        /* Define the shared list of monkeys */
        
        monkeys.clear();
        monkeys.addAll(monkeyz);
    }
    
    public void add(Item item) { items.add(item); }
    
    public void inspectAll() {
        /* Inspects every item the monkey is holding.
         * Divides worry level by 3 before modifying
         */
        
        for (Item item : items) {
            item.worry = operation.applyAsInt(item.worry) / 3;
            int index = (item.worry % test == 0) ? yes : no;
            monkeys.get(index).add(item);
            
            total++;
        }
        
        items.clear();
    }
    
    public void inspectAll(int modulo) {
        /* Inspects every item the monkey is holding.
         * Does not divide by 3. Instead, applies given modulo
         */
        
        for (Item item : items) {
            item.worry = operation.applyAsInt(item.worry) % modulo;
            int index = (item.worry % test == 0) ? yes : no;
            monkeys.get(index).add(item);
            
            total++;
        }
        
        items.clear();
    }
    
    public String toString() {
        return String.format("{items=%s, test=%d, yes=%d, no=%d, total=%d}", items, test, yes, no, total);
    }
}

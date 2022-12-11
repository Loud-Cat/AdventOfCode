import java.util.List;
import java.util.ArrayList;

/* Circuit
 * Used to represent the clock circuit of a CRT's CPU */

public class Circuit {
    private int x;
    private int cycle;
    private List<Integer> checkpoints;
    private char[][] screen;
    
    public Circuit() {
        x = 1;
        cycle = 0;
        checkpoints = new ArrayList<>();
        screen = new char[6][40];
    }
    
    public List<Integer> getCheckpoints() { return checkpoints; }
    public char[][] getScreen() { return screen; }
    
    public void next(int time) {
        for (int i = 0; i < time; i++) {
            cycle += 1;
            
            if (cycle == 20 || (cycle + 20) % 40 == 0)
                checkpoints.add(cycle * x);
            
            int row = (cycle - 1) / 40;
            int crt = (cycle - 1) % 40;
            screen[row][crt] = (Math.abs(crt - x) < 2) ? '#' : '.';
        }
    }
    
    public void add(int v) {
        x += v;
    }
    
    public int sum() {
        return checkpoints.stream().mapToInt(Integer::intValue).sum();
    }
}

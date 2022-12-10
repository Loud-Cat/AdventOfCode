
/* Node
 * Used to represent an individual "tail" on a rope.
 * Follows its parent, if any. */

public class Node {
    public Node parent;
    int x, y;
    
    public Node(Node parent) {
        this.parent = parent;
        this.x = 0;
        this.y = 0;
    }
    
    public boolean isTouching() {
        /* Returns if the given tail is touching its parent */
        
        if (parent == null)
            return false;
        
        for (int a = x - 1; a <= x + 1; a++)
            for (int b = y - 1; b <= y + 1; b++)
                if (a == parent.x && b == parent.y)
                    return true;
        
        return false;
    }
    
    public void move(int xd, int yd) {
        if (parent == null) {
            x += xd;
            y += yd;
            return;
        }
        
        parent.move(xd, yd);
        
        // calculate difference in x and y
        int dx = parent.x - x;
        int dy = parent.y - y;
        
        /* "If the head is ever two steps directly
         * up, down, left, or right from the tail,
         * the tail must also move one step in that direction..." */
        if (Math.abs(dx) == 2 && dy == 0)
            x += (x < parent.x) ? 1 : -1;
        else if (Math.abs(dy) == 2 && dx == 0)
            y += (y < parent.y) ? 1 : -1;
        
        /* "Otherwise, if the head and tail aren't touching
         * and aren't in the same row or column,
         * the tail always moves one step diagonally" */
        else if ( !isTouching() ) {
            if (dx != 0) x += (x < parent.x) ? 1 : -1;
            if (dy != 0) y += (y < parent.y) ? 1 : -1;
        }
        
    }
}

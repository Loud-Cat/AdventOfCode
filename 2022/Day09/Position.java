
public class Position {
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

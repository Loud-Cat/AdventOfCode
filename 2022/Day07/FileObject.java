/* File Object
 * Used to represent a file
 */

public class FileObject {
    private FolderObject parent;
    private String name;
    private int size;
    
    public FileObject(FolderObject parent, String name, int size) {
        this.parent = parent;
        this.name = name;
        this.size = size;
    }
    
    public FolderObject getParent() { return parent; }
    public String getName() { return name; }
    public int getSize() { return size; }
    
    @Override
    public String toString() {
        return String.format("File[name=%s, size=%d]", name, size);
    }
}

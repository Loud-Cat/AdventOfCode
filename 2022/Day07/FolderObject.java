/* Folder Object
 * Used to represent either a file or another folder (dir) */

public class FolderObject {
    private FolderObject parent;
    private String name;
    
    private Deque<FolderObject> folders;
    private Deque<FileObject> files;
    
    public FolderObject(FolderObject parent, String name) {
        this.parent = parent;
        this.name = name;
        
        this.folders = new ArrayDeque<>();
        this.files = new ArrayDeque<>();
    }
    
    public FolderObject get(String dir) {
        for (FolderObject child : folders)
            if (child.getName().equals(dir) )
                return child;
        
        return null;
    }
    
    public boolean contains(String dir) {
        for (FolderObject children : folders)
            if ( children.getName().equals(dir) )
                return true;
        
        return false;
    }
    
    public int getSize() {
        int out = 0;
        
        for (FolderObject folder : folders)
            out += folder.getSize();
        
        for (FileObject file : files)
            out += file.getSize();
        
        return out;
    }
    
    public List<FolderObject> allFolders() {
        List<FolderObject> out = new ArrayList<>();
        out.addAll(folders);
        
        for (FolderObject folder : folders)
            out.addAll( folder.allFolders() );
        
        return out;
    }
    
    public FolderObject getParent() { return parent; }
    public String getName() { return name; }
    
    public Deque<FolderObject> getFolders() { return folders; }
    public Deque<FileObject> getFiles() { return files;}
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FolderObject other)
            return other.getName().equals(name);
        return false;
    }
    
    @Override
    public int hashCode() { return 0; }
    
    @Override
    public String toString() {
        return String.format("Folder[name=%s, folders=%s, files=%s]", name, folders, files);
    }
}

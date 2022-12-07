import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import java.util.Deque;
import java.util.ArrayDeque;

/* Advent of Code
 * Day 7 - No Space Left On Device
 * NOTE: classes FileObject and FolderObject are defined below. */

public class Day7 {
    public static FolderObject getDirectory() throws IOException {
        String file = "Inputs" + File.separator + "input7.txt";
        BufferedReader reader = new BufferedReader( new FileReader(file) );
        
        FolderObject directory = new FolderObject(null, "/");
        FolderObject currentDir = directory;
        boolean displaying = false;
        
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            if ( line.startsWith("$") )
                displaying = false;
            
            if ( line.startsWith("$ cd") ) {
                String dir = line.substring(5);
                if ( dir.equals("/") )
                    currentDir = directory;
                else if ( dir.equals("..") )
                    currentDir = currentDir.getParent();
                else
                    currentDir = currentDir.get(dir);
            }
            
            else if ( line.equals("$ ls") )
                displaying = true;
            
            else if (displaying) {
                if ( line.startsWith("dir") ) {
                    String dir = line.substring(4);
                    currentDir.getFolders().addLast( new FolderObject(currentDir, dir) );
                }
                else {
                    int space = line.indexOf(" ");
                    int size = Integer.parseInt( line.substring(0, space) );
                    String name = line.substring(space + 1);
                    currentDir.getFiles().addLast( new FileObject(currentDir, name, size) );
                }
            }
            
        }
        
        return directory;
    }
    
    public static int partOne() throws IOException {
        FolderObject directory = getDirectory();
        
        int out = 0;
        for (FolderObject folder : directory.allFolders()) {
            int size = folder.getSize();
            if (size <= 100_000)
                out += size;
        }
        return out;
    }
    
    public static int partTwo() throws IOException {
        FolderObject directory = getDirectory();
        List<FolderObject> all = new ArrayList<>( directory.allFolders() );
        all.sort( Comparator.comparing(FolderObject::getSize) );
        
        int remaining = 70_000_000 - directory.getSize();
        for (FolderObject folder : all)
            if (remaining + folder.getSize() >= 30_000_000)
                return folder.getSize();
        
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        int one = partOne();
        int two = partTwo();
        long time = System.currentTimeMillis() - start;
        
        System.out.println("Part 1: " + one);
        System.out.println("Part 2: " + two);
        System.out.printf("Completed in %d ms%n", time);
    }
}

// --------------------

/* File Object
 * Used to represent a file
 * Contains a name (string) and size (int)
 */

class FileObject {
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
    public String toString() { return String.format("File[name=%s, size=%d]", name, size); }
}

// ----------

/* Folder Object
 * Used to represent either a file or another folder (dir) */

class FolderObject {
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
    
    public Deque<FolderObject> allFolders() {
        Deque<FolderObject> out = new ArrayDeque<>();
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

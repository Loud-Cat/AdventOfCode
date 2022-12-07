import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/* Advent of Code
 * Day 7 - No Space Left On Device */

public class MyClass {
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
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        int one = partOne();
        long time = System.currentTimeMillis() - start;
        
        System.out.println("Part 1: " + one);
        System.out.printf("Completed in %d ms%n", time);
    }
}

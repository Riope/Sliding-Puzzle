import java.io.*;
import java.util.*;

class SlidingPuzzle
{
    public static void main(String args[]) throws IOException
    {
        stage slide = new stage();
        slide = slide.input();
        Solvability chk = new Solvability();
        if(!chk.check(slide))
        {
            System.out.println("Not Possible");
            return;
        }
        System.out.println();
        File file = new File("other_outputs.txt");
        try
        {
            boolean result = file.createNewFile();  //creates a new file
            if (result)      // test if successfully created a new file
                System.out.println("File created at: ["+file.getCanonicalPath()+"]\n"); //returns the path string
            else
                System.out.println("File already exist at location: ["+file.getCanonicalPath()+"]\n");
        }
        catch (IOException e)   
        {
            e.printStackTrace();    //prints exception if any
        }
        PrintStream stream = new PrintStream(file);
        PrintStream defStream = System.out;
        
        long startTime = System.currentTimeMillis();
        IDAstar obj1 = new IDAstar(slide.mat.length);
        obj1.IDAstarSearch(slide);
        double timeElapsed = (System.currentTimeMillis()-startTime)/1000.0;
        System.out.println("Time taken by IDA*: " + timeElapsed);
        
        System.setOut(stream);
        startTime = System.currentTimeMillis();
        Astar obj2 = new Astar(slide.mat.length);
        System.out.println("Moves for shortest solution by A*:\n");
        obj2.AstarSearch(slide);
        timeElapsed = (System.currentTimeMillis()-startTime)/1000.0;
        System.setOut(defStream);
        System.out.println("Time taken by A*: " + timeElapsed);
        
        System.setOut(stream);
        startTime = System.currentTimeMillis();
        Iddfs obj3 = new Iddfs(slide.mat.length);
        System.out.println("Moves for shortest solution by Iddfs:\n");
        obj3.IddfsSearch(slide);
        timeElapsed = (System.currentTimeMillis()-startTime)/1000.0;
        System.setOut(defStream);
        System.out.println("Time taken by Iddfs: " + timeElapsed);
        
        System.setOut(stream);
        startTime = System.currentTimeMillis();
        bfs obj4 = new bfs(slide.mat.length);
        System.out.println("Moves for shortest solution by bfs:\n");
        obj4.bfsSearch(slide);
        timeElapsed = (System.currentTimeMillis()-startTime)/1000.0;
        System.setOut(defStream);
        System.out.println("Time taken by bfs: " + timeElapsed);
    }
}
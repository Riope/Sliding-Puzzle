import java.io.*;
import java.util.*;

public class Iddfs
{
    int n;
    HashSet<Double> set;
    double finalcode;
    
    Iddfs(int n)
    {
        this.n = n;
        finalcode = 0;
        double c=1;
        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
            {
                if (i==n-1 && j==n-1)
                    continue;
                finalcode += (i*n + j + 1) * c;
                c *= n*n;
            }
        }
        set = new HashSet<Double>();
    }
    
    int diff(int a, int b)
    {
        return (Math.abs(a-b));
    }
    
    double hash(int arr[][])
    {
        double sum=0,c=1;
        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
            {
                sum += arr[i][j] * c;
                c *= n*n;
            }
        }
        return sum;
    }

    boolean inLimit(int x, int y)
    {
        if (x>=0 && x<n && y>=0 && y<n)
            return true;
        return false;
    }
    
    boolean dfs(stage root, int level, int depth)
    {
        if (root.code==finalcode)
        {
            System.out.println("Min moves to solve: " + root.moves + "\n");
            root.printPath();
            return true;
        }
        if (level==depth)
            return false;

        int row[] = {0,0,1,-1};
        int col[] = {1,-1,0,0};

        for (int i=0; i<4; i++)
        {
            if (inLimit(root.x+row[i],root.y+col[i]))
            {
                stage newnode = root.newNode(row[i],col[i]);
                if (set.contains(newnode.code))
                    continue;
                set.add(newnode.code);
                if (dfs(newnode,level+1,depth))
                    return true;
            }
        }  
        return false;
    }
    
    void IddfsSearch(stage root)
    {
        root.code = hash(root.mat);
        set.add(root.code);

        //Iddfs algorithm
        int i = 1;
        while(!dfs(root,0,i))
        {
            set.clear();
            set.add(root.code);
            i++;
        }
    }

    public static void main(String args[]) throws IOException
    {
        stage slide = new stage();
        slide = slide.input();
        long startTime = System.currentTimeMillis();
        Solvability chk = new Solvability();
        if(!chk.check(slide))
        {
            System.out.println("Not Possible");
            return;
        }
        System.out.println();
        Iddfs obj = new Iddfs(slide.mat.length);
        obj.IddfsSearch(slide);
        double timeElapsed = (System.currentTimeMillis()-startTime)/1000.0;
        System.out.println("Time taken: " + timeElapsed);
    }
}
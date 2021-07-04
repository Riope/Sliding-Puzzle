import java.io.*;
import java.util.*;

public class IDAstar
{
    int n;
    int min;
    HashSet<Double> set;
    
    IDAstar(int n)
    {
        this.n = n;
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
    
    int manhattanDist(int mat[][])
    {
        int c=0;
        for (int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(mat[i][j]>0)
                    c += diff((mat[i][j]-1)/n, i) + diff((mat[i][j]-1)%n, j);
            }
        }
        return c;
    }

    boolean inLimit(int x, int y)
    {
        if (x>=0 && x<n && y>=0 && y<n)
            return true;
        return false;
    }
    
    boolean dfs(stage root, int level, int depth)
    {
        if (root.dist==0)
        {
            System.out.println("Min moves to solve: " + root.moves + "\n");
            root.printPath();
            return true;
        }
        if (root.moves + root.dist > depth)
        {
            if (root.moves + root.dist < min)
                min = root.moves + root.dist;
            return false;
        }
        if (level==depth)
            return false;

        PriorityQueue<stage> pq = new PriorityQueue<stage>();
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
                newnode.dist = this.manhattanDist(newnode.mat);
                pq.add(newnode);
            }
        }

        while (!pq.isEmpty())
            if (dfs(pq.poll(),level+1,depth))
                return true;
        return false;
    }

    void IDAstarSearch(stage root)
    {
        root.dist = this.manhattanDist(root.mat);
        root.code = hash(root.mat);
        set.add(root.code);

        //IDA* algorithm
        int i = root.dist;
        this.min = Integer.MAX_VALUE;
        while(!dfs(root,0,i))
        {
            set.clear();
            set.add(root.code);
            i = this.min;
            this.min = Integer.MAX_VALUE;
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
        IDAstar obj = new IDAstar(slide.mat.length);
        obj.IDAstarSearch(slide);
        double timeElapsed = (System.currentTimeMillis()-startTime)/1000.0;
        System.out.println("Time taken: " + timeElapsed);
    }
}
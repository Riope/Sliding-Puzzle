import java.io.*;
import java.util.*;

public class Astar
{
    int n;
    int finalMatrix[][];
    
    Astar(int n)
    {
        this.n = n;
        finalMatrix = new int[n][n];
        for(int i=0;i<n;i++)
            for(int j=0; j<n; j++)
                finalMatrix[i][j] = i*n + j + 1;
        finalMatrix[n-1][n-1] = 0;
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
    
    int misplacedTiles(int mat[][])
    {
        int c=0;
        for (int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(mat[i][j]>0 && mat[i][j] != this.finalMatrix[i][j])
                    c++;
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
    
    void AstarSearch(stage root)
    {
        int row[] = {0,0,1,-1};
        int col[] = {1,-1,0,0};
    
        PriorityQueue<stage> pq = new PriorityQueue<stage>();
        HashSet<Double> set = new HashSet<Double>();
        root.dist = this.misplacedTiles(root.mat);
        pq.add(root);
        root.code = hash(root.mat);
        set.add(root.code);
    
        //A* algorithm
        while(!pq.isEmpty())
        {
            stage node = pq.poll();
            if (node.dist == 0)
            {
                System.out.println("Min moves to solve: " + node.moves + "\n");
                node.printPath();
                return;
            }   
            for (int i=0; i<4; i++)
            {
                if (inLimit(node.x+row[i],node.y+col[i]))
                {
                    stage newnode = node.newNode(row[i],col[i]);
                    if (set.contains(newnode.code))
                        continue;
                    set.add(newnode.code);
                    newnode.dist = this.misplacedTiles(newnode.mat);
                    pq.add(newnode);
                }
            }
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
        Astar obj = new Astar(slide.mat.length);
        obj.AstarSearch(slide);
        double timeElapsed = (System.currentTimeMillis()-startTime)/1000.0;
        System.out.println("Time taken: " + timeElapsed);
    }
}
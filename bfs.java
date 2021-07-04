import java.io.*;
import java.util.*;

public class bfs
{
    int n;
    int finalMatrix[][];
    double finalcode;
    
    bfs(int n)
    {
        this.n = n;
        finalMatrix = new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                finalMatrix[i][j] = i*n + j + 1;
        finalMatrix[n-1][n-1] = 0;
        finalcode = hash(finalMatrix);
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

    void bfsSearch(stage root)
    {
        int row[] = {0,0,1,-1};
        int col[] = {1,-1,0,0};
    
        Queue<stage> pq = new LinkedList<stage>();
        HashSet<Double> set = new HashSet<Double>();
        pq.add(root);
        root.code = hash(root.mat);
        set.add(root.code);
        
        //bfs algorithm
        while (!pq.isEmpty())
        {
            stage node = pq.poll();
            if (node.code == finalcode)
            {
                System.out.println("Min moves to solve: " + node.moves + "\n");
                node.printPath();
                return;
            }
            for (int i=0; i<4; i++)
            {
                if (inLimit(node.x+row[i], node.y+col[i]))
                {
                    stage newnode = node.newNode(row[i],col[i]);
                    if (set.contains(newnode.code))
                        continue;
                    set.add(newnode.code);
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
        bfs obj = new bfs(slide.mat.length);
        obj.bfsSearch(slide);
        double timeElapsed = (System.currentTimeMillis()-startTime)/1000.0;
        System.out.println("Time taken: " + timeElapsed);
    }
}
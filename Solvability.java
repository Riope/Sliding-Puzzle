import java.util.*;

class Solvability
{
    int merge(int[] arr, int l, int m, int r)
    {
        int left[] = Arrays.copyOfRange(arr, l, m + 1);
        int right[] = Arrays.copyOfRange(arr, m + 1, r + 1);
        int i=0, j=0, k=l, swaps=0;
 
        while (i < left.length && j < right.length)
        {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else
            {
                arr[k++] = right[j++];
                swaps += (m+1) - (l+i);
            }
        }
        while (i < left.length)
            arr[k++] = left[i++];
        while (j < right.length)
            arr[k++] = right[j++];
        return swaps;
    }

    int mergeSortAndCount(int[] arr, int l, int r)
    {
        int count = 0;
        if (l < r)
        {
            int m = (l + r) / 2;
            count += mergeSortAndCount(arr, l, m);
            count += mergeSortAndCount(arr, m + 1, r);
            count += merge(arr, l, m, r);
        }
        return count;
    }

    int inversion(int arr[][])
    {
        int n = arr.length;
        int ar[] = new int[n*n-1];
        int c = 0;
        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
            {
                if (arr[i][j]!=0)
                    ar[i*n+j+c] = arr[i][j];
                else
                    c--;
            }
        }
        return mergeSortAndCount(ar, 0, n*n-2);
    }
    
    boolean check(stage root)
    {
        int n = root.mat.length;
        int cnt = inversion(root.mat);
        if (n%2==0)
            return ((n-root.x)%2 != cnt%2);
        return (cnt%2 == 0);
    }
}
package question_bank.num363_hard_MaxSumOfRectangleNoLargerThanK;

import java.util.TreeSet;

public class Main {

    public static void main (String [] args){

    }
}



/**1171ms*/

/* first  consider the situation matrix is 1D
    we can save every sum of 0~i(0<=i<len) and binary search previous sum to find
    possible result for every index, time complexity is O(NlogN).
    so in 2D matrix, we can sum up all values from row i to row j and create a 1D array
    to use 1D array solution.
    If col number is less than row number, we can sum up all values from col i to col j
    then use 1D array solution.
*/

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;
        int m = Math.min(row, col);
        int n = Math.max(row, col);
        //indicating sum up in every row or every column
        boolean colIsBig = col > row;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            int[] array = new int[n];
            // sum from row j to row i
            for (int j = i; j >= 0; j--) {
                int val = 0;
                TreeSet<Integer> set = new TreeSet<Integer>();
                set.add(0);
                //traverse every column/row and sum up
                for (int k = 0; k < n; k++) {
                    array[k] = array[k] + (colIsBig ? matrix[j][k] : matrix[k][j]);
                    val = val + array[k];
                    //use  TreeMap to binary search previous sum to get possible result
                    Integer subres = set.ceiling(val - target);
                    if (null != subres) {
                        res = Math.max(res, val - subres);
                    }
                    set.add(val);
                }
            }
        }
        return res;
    }
}


/**7ms*/

class Solution2 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0) {
            return -1;
        }
        int m = matrix.length, n = matrix[0].length;
        int[] sums = new int[m + 1];
        int ans = Integer.MIN_VALUE;
        int i, j, s;

        for(i = 0; i < n; i++) {
            int[] A = new int[m]; // 易错点: A 从 i 固定开始是一个新的
            for(j = i; j < n; j++) {
                int max = Integer.MIN_VALUE, sub = 0;
                for(s = 0; s < m; s++) {
                    A[s] += matrix[s][j];
                    sums[s + 1] = sums[s] + A[s];
                    // Kadane Algo maximum subarray sum
                    sub = Math.max(sub + A[s], A[s]);
                    max = Math.max(max, sub);
                }
                if(max <= k) { // 子数组最大和大于 k 才去搜寻所有组合
                    ans = Math.max(ans, max);
                }else {
                    ans = Math.max(ans, mergeSort(sums, 0, m, k));
                }
            }
        }
        return ans;
    }

    private int mergeSort(int[] sum, int start, int end, int k) {
        if(start >= end) {
            return Integer.MIN_VALUE;
        }
        int mid = start + (end - start) / 2;
        int ans = mergeSort(sum, start, mid, k); // 易错点，这里用 mid - 1，当start,end 相邻时，right[mid,end] 会爆栈
        if(ans == k) {
            return ans;
        }
        ans = Math.max(ans, mergeSort(sum, mid + 1, end, k));
        if(ans == k) {
            return ans;
        }

        int[] temp = new int[end - start + 1];
        int i, j = mid + 1, t = 0;
        int p = mid + 1;
        for(i = start; i <= mid; i++) {
            while(p <= end && sum[p] - sum[i] <= k) {
                ans = Math.max(ans, sum[p] - sum[i]);
                p++;
            }
            while(j <= end && sum[j] < sum[i]) temp[t++] = sum[j++];
            temp[t++] = sum[i];
        }
        System.arraycopy(temp, 0, sum, start, t);
        return ans;
    }
}


/**141ms*/
/*
 * If # of columns is smaller, process one set of columns [i..j) at a time, for each different i<j.
 * For one set of colums [i..j), do it like "Count of Range Sum".
 * O(n) = n^2 * mlogm.
 * Assume we have such result.
 */

class Solution3 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length, ans = Integer.MIN_VALUE;
        long[] sum = new long[m+1]; // stores sum of rect[0..p][i..j]
        for (int i = 0; i < n; ++i) {
            long[] sumInRow = new long[m];
            for (int j = i; j < n; ++j) { // for each rect[*][i..j]
                for (int p = 0; p < m; ++p) {
                    sumInRow[p] += matrix[p][j];
                    sum[p+1] = sum[p] + sumInRow[p];
                }
                ans = Math.max(ans, mergeSort(sum, 0, m+1, k));
                if (ans == k) return k;
            }
        }
        return ans;
    }
    int mergeSort(long[] sum, int start, int end, int k) {
        if (end == start+1) return Integer.MIN_VALUE; // need at least 2 to proceed
        int mid = start + (end - start)/2, cnt = 0;
        int ans = mergeSort(sum, start, mid, k);
        if (ans == k) return k;
        ans = Math.max(ans, mergeSort(sum, mid, end, k));
        if (ans == k) return k;
        long[] cache = new long[end-start];
        for (int i = start, j = mid, p = mid; i < mid; ++i) {
            while (j < end && sum[j] - sum[i] <= k) ++j;
            if (j-1 >= mid) {
                ans = Math.max(ans, (int)(sum[j-1] - sum[i]));
                if (ans == k) return k;
            }
            while (p < end && sum[p] < sum[i]) cache[cnt++] = sum[p++];
            cache[cnt++] = sum[i];
        }
        System.arraycopy(cache, 0, sum, start, cnt);
        return ans;
    }
}

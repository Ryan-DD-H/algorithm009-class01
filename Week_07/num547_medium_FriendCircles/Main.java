package question_bank.num547_medium_FriendCircles;


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        int[][] M = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        };

        //Solution solution = new Solution();
        //System.out.println(solution.findCircleNum(M));

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.findCircleNum(M));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.findCircleNum(M));
    }

}


/**
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * <p>
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * 示例 2:
 * <p>
 * 输入:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 注意：
 * <p>
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/friend-circles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


/**
 * 失败
 * */
class Solution {
    public int findCircleNum(int[][] M) {

        if (M == null || M.length == 0)
            return 0;
        int len = M.length;
        int[] relation = new int[len];
        for (int i = 0; i < len; i++) {

            for (int j = len - 1; j > i; j--) {

                if (M[i][j] != 0) {

                    if (relation[i] == 0 && relation[j] == 0) {
                        relation[i] = relation[j] = relation[j] + 1;
                    } else if (relation[i] != 0)
                        relation[j] = relation[i];
                    else if (relation[j] != 0)
                        relation[i] = relation[j];
                }

            }
        }
        int circle = 0;
        int max = 0;
        for (int num : relation) {
            if (num == 0)
                circle++;
            else
                max = num;
        }
        return circle + max;
    }

/*  boolean dfs(boolean [][] visited, int i, int j){


  }*/
}


class Solution01 {
    public int findCircleNum(int[][] M) {
        /**
         使用一个visited数组, 依次判断每个节点, 如果其未访问, 朋友圈数加1并对该节点进行dfs搜索标记所有访问到的节点
         **/
        boolean[] visited = new boolean[M.length];
        int ret = 0;
        for (int i = 0; i < M.length; ++i) {
            if (!visited[i]) {
                dfs(M, visited, i);
                ret++;
            }
        }
        return ret;
    }

    private void dfs(int[][] m, boolean[] visited, int i) {
        for (int j = 0; j < m.length; ++j) {
            if (m[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(m, visited, j);
            }
        }
    }
}

class Solution02 {

    int find(int parent[], int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    void union(int parent[], int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        if (xset != yset)
            parent[xset] = yset;
    }

    public int findCircleNum(int[][] M) {
        int[] parent = new int[M.length];
        Arrays.fill(parent, -1);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && i != j) {
                    union(parent, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1)
                count++;
        }
        return count;
    }
}

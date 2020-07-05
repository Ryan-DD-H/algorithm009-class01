package question_bank.num70_easy_ClimbingStairs;


public class Main {


  public static void main(String [] args){


    Solution solution = new Solution();
    System.out.println(solution.climbStairs(6));


    Solution02 solution02 = new Solution02();
    System.out.println(solution02.climbStairs(6));
  }
}


/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */


/**
 * 回溯
 * */
class Solution {
  public int climbStairs(int n) {

    if (n <= 2)
      return n;
    int [] mem = new int[n];
    mem[0] = 1;
    mem[1] = 2;
    for(int i = 2; i < n; i++)
      mem[i] = mem[i-1] + mem[i-2];
    return mem[n-1];
  }
}



/**
 * 动态规划
 * */
class Solution02 {
  public int climbStairs(int n) {

    if (n <= 2)
      return n;
    int allWays = 0;
    int oneStep = 2;
    int twoStep = 1;

    for(int i = 2; i < n; i++){
      allWays = oneStep + twoStep;
      twoStep = oneStep;
      oneStep = allWays;

    }
    return allWays;
  }
}
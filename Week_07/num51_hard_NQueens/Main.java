package question_bank.num51_hard_NQueens;

import java.util.*;

public class Main {

  public static void  main(String [] args){

    //Solution solution = new Solution();
    //System.out.println(solution.solveNQueens(4));
    Solution01 solution01 = new Solution01();
    System.out.println(solution01.solveNQueens(4));
  }
}


/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */


/**
 * 想的简单了，算法错了，本来还想偷懒的
 * */
class Solution {
  public List<List<String>> solveNQueens(int n) {

    if( n == 1){
      List<List<String>> l = new ArrayList<>();
      List<String> ll = new ArrayList<>();
      ll.add("Q");
      l.add(ll);
      return l;
    }
    if( n < 4)
      return new ArrayList<>();
    List<List<String>> list = new ArrayList<>();
    dfs(null, n, 0, null, list);

    return list;
  }

  public void dfs(List<Integer> i, int n, int depth, List<String> string, List<List<String>> list){

    List<String> strList;

    if(depth == 0) {
      for (int a = 0; a < n; a++) {
          List<Integer> ii = new ArrayList<>();
          strList = new ArrayList<>();
          strList.add(join(a, n));
          ii.add(a);
          dfs(ii, n, depth + 1, strList, list);
      }
    }

    if(depth != 0 && depth != n-1) {
      for (int a = 0; a < n; a++) {
        int c = i.get(i.size()-1);
        if (a != c-1 && a != c+1 && (!i.contains(a))){
          List<Integer> ii = new ArrayList<>(i);
          strList = new ArrayList<>(string);
          ii.add(a);
          String str = join(a, n);
          strList.add(str);
          dfs(ii,n, depth + 1, strList, list);
        }
      }
    }

    if(depth == n - 1) {
      for (int a = 0; a < n; a++) {
        int c = i.get(i.size()-1);
        if (a != c-1 && a != c+1 && (!i.contains(a))){
          strList = new ArrayList<>(string);
          String str = join(a, n);
          strList.add(str);
          list.add(strList);
        }
      }
    }
  }


/*  public String join(int j, int n) {

    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < n; i++){
      if (i != j)
        sb.append(".");
      else
        sb.append("Q");
    }
    return sb.toString();
  }*/

  public String join(int j, int n) {

    char [] row = new char[n];
    Arrays.fill(row,'.');
    row[j] = 'Q';
    return new String(row);
  }
}





class Solution01 {
  /**
   * 皇后可以攻击竖着的横着的,左斜线,右斜线的对手.
   *
   * @param n
   * @return
   */
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    if (n == 0) return result;
    Set<Integer> pie = new HashSet<>();
    Set<Integer> na = new HashSet<>();
    dfs(0,n,pie,na,new HashSet<>(),result,new ArrayList<>());
    return result;
  }

  private void dfs(int level, int n, Set<Integer> pie, Set<Integer> na,Set<Integer> col,List<List<String>> results, List<String> tempList) {
    if (level > n - 1) {
      if (tempList.size() == n){
        List<String> result = new ArrayList<>(tempList);
        results.add(result);
      }
      return;
    }

    boolean exists = false;
    // 遍历列
    for (int j = 0; j < n; j++) {
      if (pie.contains(level + j) || na.contains(j - level) || col.contains(j)) {// 会被攻击的位置!
        continue;
      }

      exists = true;

      pie.add(level + j);
      na.add(j - level);
      col.add(j);

      String temp = getStr(n,j);

      tempList.add(temp);

      dfs(level + 1,n,pie,na,col,results,tempList);

      pie.remove(level + j);
      na.remove(j - level);
      col.remove(j);
      tempList.remove(temp);
    }
    if (!exists){
      return;
    }
  }

  private String getStr(int n, int j) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < n; i++) {
      if (i == j){
        stringBuilder.append("Q");
      }else
        stringBuilder.append(".");
    }
    return stringBuilder.toString();
  }
}
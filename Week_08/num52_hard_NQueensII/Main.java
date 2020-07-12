package question_bank.num52_hard_NQueensII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

  public static void  main(String [] args){
    Solution solution = new Solution();
    System.out.println(solution.totalNQueens(5));
  }
}


class Solution {
  /**
   * 皇后可以攻击竖着的横着的,左斜线,右斜线的对手.
   *
   * @param n
   * @return
   */
  public int totalNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    if (n == 0) return result.size();
    Set<Integer> pie = new HashSet<>();
    Set<Integer> na = new HashSet<>();
    dfs(0,n,pie,na,new HashSet<>(),result,new ArrayList<>());

    return result.size();
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
package question_bank.num22_medium_GenerateParentheses;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String [] args){

    Solution solution = new Solution();
    System.out.println(solution.generateParenthesis(3));
  }

}

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */


class Solution {
  public List<String> generateParenthesis(int n) {
    List<String> list = new ArrayList<>();
    create("",list,n,n);
    return list;
  }

  public void create(String sb, List<String> list, int left, int right){

    if (right == 0){
      list.add(sb);
      return;
    }
    if (left > 0)
      create(sb + "(", list,left - 1, right);
    if (right > left)
      create(sb + ")", list, left,right - 1);
  }
}
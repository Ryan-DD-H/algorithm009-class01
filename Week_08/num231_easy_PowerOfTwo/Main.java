package question_bank.num231_easy_PowerOfTwo;

public class Main {

  public static void main(String [] args){

    int num = 536870912;

    Solution solution = new Solution();
    System.out.println(solution.isPowerOfTwo(num));

    Solution01 solution01 = new Solution01();
    System.out.println(solution01.isPowerOfTwo(num));

    Solution02 solution02 = new Solution02();
    System.out.println(solution02.isPowerOfTwo(num));
  }
}

/**
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: true
 * 解释: 20 = 1
 * 示例 2:
 *
 * 输入: 16
 * 输出: true
 * 解释: 24 = 16
 * 示例 3:
 *
 * 输入: 218
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-two
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */



/**
 * 不停的取余
 * */
class Solution {
  public boolean isPowerOfTwo(int n) {
    if(n <= 0)
      return false;
    while(n != 0){
      int i;
      i = n % 2;
      n = n >> 1;
      if(i == 1 & n!= 0)
        return false;
    }
    return true;
  }
}



/**
 * 直接使用对数运算
 * 不过好像因为精度问题，失败了
 * */
class Solution01 {
  public boolean isPowerOfTwo(int n) {

    double a = Math.log(n)/Math.log(2);
    double b = (int) a;

    String aa = Double.toString(a);
    String bb = Double.toString(b);
    return aa.equals(bb);
  }
}


/**
 * 使用位运算  x&(x-1) :位运算，除去最低位的1；
 * */
class Solution02 {
  public boolean isPowerOfTwo(int n) {
    return n > 0 && (n&(n-1)) == 0;
  }
}
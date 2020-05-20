package num283_easy_MoveZeroes;

import java.util.Arrays;

public class Main {

    public static void main(String [] args){

        Solution solution = new Solution();
        int [] a = {0,1,0,3,12};
        solution.moveZeroes(a);
        System.out.println(Arrays.toString(a));

    }

}


/**
 *给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */


class Solution {
    public void moveZeroes(int[] nums) {
        //isZero,当isZero与pointer不相等的时候，isZero指向的就是零存在的位置
        //pointer指向的是当前循环指向的位置
        //如果pointer指向的元素为零则跳过；如果指向的位置的值不为零，则将其值与isZero所指向的位置的值交换。
        int isZero = 0;
        int length = nums.length;
             for (int pointer = 0; pointer < length; pointer++) {
            if (nums[pointer] != 0 && pointer != isZero++){
                nums[isZero-1] = nums[pointer];
                nums[pointer] = 0;
            }
        }
    }
}

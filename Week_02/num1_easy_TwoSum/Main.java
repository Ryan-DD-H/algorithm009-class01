package question_bank.num1_easy_TwoSum;

import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String [] args) {

        int [] nums = {3,2,4};
        int target = 6;
//        Solution solution = new Solution();
//        System.out.println(Arrays.toString(solution.twoSum(nums,target)));

        Solution02 solution02 = new Solution02();
        System.out.println(Arrays.toString(solution02.twoSum(nums,target)));
    }
}


/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */

/**
 * 暴力破解
 * */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0;i<nums.length-1;i++){
            for(int j = i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target)
                    return new int[]{i,j};
            }
        }
        return new int[0];
    }
}

/**
 * 使用HashMap<数组元素,数组下标> :更快
 * */
class Solution02 {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer,Integer> hashMap = new HashMap<>();

        for (int i = 0;i < nums.length;i++)
            hashMap.put(nums[i],i);

        for(int i = 0;i < nums.length;i++){
            if(hashMap.containsKey(target - nums[i])){
                int k = hashMap.get(target - nums[i]);
                if(k != i)
                    return new int[]{i,k};
            }
        }
        return new int[0];
    }
}
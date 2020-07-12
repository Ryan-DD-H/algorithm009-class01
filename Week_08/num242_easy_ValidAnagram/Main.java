package question_bank.num242_easy_ValidAnagram;

import java.util.Arrays;
import java.util.HashMap;

public class Main {



    public static void main(String [] args) {

        String s = "aacz";
        String t = "acaz";
//        Solution solution = new Solution();
//        System.out.println(solution.isAnagram(s,t));


//        Solution01 solution01 = new Solution01();
//        System.out.println(solution01.isAnagram(s,t));


        //HashMap sMap = new HashMap<Character,Integer>();
        //sMap.put('a',2);
        //System.out.println(sMap.get('a'));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.isAnagram(s,t));

    }
}


/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */

/**
 * 使用排序
 * */
class Solution {
    boolean isAnagram(String s, String t) {

        if(s.length() != t.length()) {
            return false;
        }else if(s.length() == 0){
            return true;
        }
        char [] schars = s.toCharArray();
        char [] tchars = t.toCharArray();
        schars = qsort(schars,0,schars.length-1);
        tchars = qsort(tchars,0,tchars.length-1);
        return Arrays.equals(schars,tchars);

    }

    char[] qsort(char arr[],int start,int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i<j) {
            while ((i<j)&&(arr[j]>pivot)) {
                j--;
            }
            while ((i<j)&&(arr[i]<pivot)) {
                i++;
            }
            if ((arr[i]==arr[j])&&(i<j)) {
                i++;
            } else {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i-1>start) arr=qsort(arr,start,i-1);
        if (j+1<end) arr=qsort(arr,j+1,end);
        return (arr);
    }

}


/**
 * 使用哈希map
 * */
class Solution01 {
    public boolean isAnagram(String s, String t) {

        if(s.length() != t.length()) {
            return false;
        }else if(s.length() == 0){
            return true;
        }

        HashMap sMap = map(s);
        HashMap tMap = map(t);

        return sMap.equals(tMap);
    }

    private HashMap<Character, Integer> map(String s){
        HashMap<Character, Integer> sMap = new HashMap<>();
        char ch;
        for(int i = 0; i < s.length(); i++){
            ch = s.charAt(i);
            if(sMap.get(ch) == null){
                sMap.put(ch,1);
            }else {
                sMap.put(ch, sMap.get(ch) + 1);
            }
        }
        return sMap;
    }
}

/**
 * 使用数组代替哈希map
 * */
class Solution02 {
    public boolean isAnagram(String s, String t) {

        if(s.length() != t.length()) {
            return false;
        }else if(s.length() == 0){
            return true;
        }
        int [] sInt = new int[26];
        int [] tInt = new int[26];
        for(char ch : s.toCharArray())
            sInt[ch - 'a'] = sInt[ch - 'a'] + 1;
        for(char ch : t.toCharArray())
            tInt[ch - 'a'] = tInt[ch - 'a'] + 1;
        return Arrays.equals(sInt,tInt);
    }

}
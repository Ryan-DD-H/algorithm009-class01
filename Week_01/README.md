### 学习笔记

#### LeetCode283.移动零

题目如下：

![image-20200520164956602](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200520164956602.png)

原来想的就是创建一个与原数组长度相同新的数组，然后循环原数组将非零元素依次添加到新元素的位置上，剩下的位置默认就是为0，所以刚好解决。结果一审题，只能在原数组上面操作。。。

然后看了下覃老师的代码：

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                nums[j] = nums[i];
                if (i != j)
                    nums[i] = 0;
                j++;
            }
        }
    }
}
```

感觉覃老师代码里面有个代码片段不是很容易理解，如下

```java
if (nums[i] != 0){
    nums[j] = nums[i];
    if (i != j)
        nums[i] = 0;
    j++;
}
```

覃老师是直接把nums[i]的值赋给了nums[j],然后判断i是否是等于j的，来确定是否把num[i]置零。我觉得如果使用交换的话，可能会更优雅一点吧。（当然，可能每个人的审美不一样，哈哈哈）

下面是我的代码：

```java
class Solution {
    public void moveZeroes(int[] nums) {
        //isZero，当isZero与pointer不相等的时候，isZero指向的是零存在的位置
        //pointer，指向的是当前循环指向的位置
        //如果pointer指向的元素为零则跳过；如果指向的位置的值不为零，则将其值与isZero所指向的位置的值交换。
        int isZero = 0;
        int length = nums.length;
        for (int pointer = 0; pointer < length; pointer++) {
            if (nums[pointer] != 0){
                int tmp = nums[pointer];
                nums[pointer] = nums[isZero];
                nums[isZero++] = tmp;
            }
        }
    }
}
```






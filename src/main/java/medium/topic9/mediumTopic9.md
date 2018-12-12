# 最接近的三数之和
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
返回这三个数的和。假定每组输入只存在唯一答案。
```
例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
```
## 解法
### 双指针法
先将输入数组排序，然后我们遍历数组，在内部我们使用双指针，分别用指针l和r指向内部循环的最大和最小值，
根据三数之和threeSum与target直接的大小关系来决定l与r的变化。
1. threeSum > target.下一次迭代l++；
2. threeSum < target.下一次迭代r--；
3. threeSum = target.threeSum即为所需值。

threeSum = nums[i] + nums[l] + nums[r]。

i = 0 ~ nums.length - 2;初值l = i + 1;初值r = nums.length - 1

时间复杂度O(n^2)
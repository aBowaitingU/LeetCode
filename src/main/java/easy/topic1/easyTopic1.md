# 两数之和
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的 两个 整数。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

## 示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9

所以返回 [0, 1]

## 思路：

建立一个Map<Integer, Integer>，key为数组nums中的每一个数值num，value为该num值在数组nums中的位置i。

遍历数组nums，对第i次迭代的值num，计算valueNeed = target - num，判断valueNeed是否已经存在于Map的key中。如果已经存在，则直接返回[Map(valueNeed), i]。

Map是在遍历数组的过程中逐步建立的，算法总共只要遍历一次数组即可。而且，不可能存在重复引用i的情况。
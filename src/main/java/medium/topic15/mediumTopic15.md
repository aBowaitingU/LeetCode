# 下一个排列
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
```
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
```


# 解法
首先，我们观察到对于任何给定序列的降序，不存在下一个更大的排列。
```
[9, 5, 4, 3, 1]
```
我们从右向左扫描找到的第一对连续的数组a[i]和a[i - 1]，使a[i] > a[i - 1]。此时，没有对a[i]右侧（包括a[i]）的重新排列
可以创建更大的排列，因为该子串已经是以降序排列的。此时，我们只能重新排列a[i - 1]右侧（包括a[i - 1]）的数字。

现在，什么样子的重新排列将产生下一个更大的数字呢？我们想要创建比当前更大的排列。因此，我们需要将数字 a[i−1] 替换为
位于其右侧区域的数字中刚好比它大的数字，比如a[j]。

我们交换a[i - 1]和a[j]，此时索引i - 1处有了正确的数字。但是目前的排列仍然不是最后结果。我们需要通过仅使用
a[i - 1]右侧（不包括a[i - 1]）的数字形成最小的排序，即升序排序，以获得最小的排列。

# 合并K个排序链表
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
```
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
```

## 解法
类似与2条排序链表的合并，本质不难。
需要着重考虑处理已经到尾端的链表。

算法

比较 k 个节点（每个链表的首节点），获得最小值的节点。
将选中的节点接在最终有序链表的后面。

复杂度分析


时间复杂度： O(kN) ，其中 k 是链表的数目。

几乎最终有序链表中每个节点的时间开销都为 O(k) （k-1 次比较）。
总共有 N 个节点在最后的链表中。



空间复杂度：

·O(n) 。创建一个新的链表空间开销为 O(n) 。
·O(1) 。重复利用原来的链表节点，每次选择节点时将它直接接在最后返回的链表后面，而不是创建一个新的节点。
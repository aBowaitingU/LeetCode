# Z 字形变换
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
```
L   C   I   R
E T O E S I I G
E   D   H   N
```
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
请你实现这个将字符串进行指定行数变换的函数：
string convert(string s, int numRows);
## 示例 1:
输入: s = "LEETCODEISHIRING", numRows = 3

输出: "LCIRETOESIIGEDHN"
## 示例 2:
输入: s = "LEETCODEISHIRING", numRows = 4

输出: "LDREOEIIECIHNTSG"

解释:
```
L     D     R
E   O E   I I
E C   I H   N
T     S     G
```
## 想法
对于一个n = numRows > 1，当将字符串转化为Z字形排列时，从上往下的第i层字符串，最左边的字符为原字符串的第i位上的字符，
从左到右的字符，在原字符串上的位置依次是i, 2 * （n - 1） - i, 2 * (n - 1) + i, 4 * (n - 1) - i, 4 * (n - 1) + i, ...，
2 * k *(n - 1) - i, 2 * k * (n - 1) + i,...

需要注意到，当i = 0或i = n - 1时， 2 * k *(n - 1) - i会与左边或者右边的点重合。即对于第一行和最后一行，我们需要分开讨论。

另外，当n = numRows = 1时，转换的结果即为原字符串。

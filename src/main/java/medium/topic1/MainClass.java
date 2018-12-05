package main.java.medium.topic1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    private boolean isOver = false;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode resultRoot = result;
        isOver = false;
        // 最开始，将两条链表相加
        while (l1 != null && l2 != null) {
            int tempResult = 0;
            if (isOver) {
                tempResult = l1.val + l2.val + 1;
                isOver = false;
            } else {
                tempResult = l1.val + l2.val;
            }
            if (tempResult >= 10) {
                tempResult -= 10;
                isOver = true;
            }
            result.val = tempResult;
            // 这一部分的逻辑是为了避免在结果链表的最后创建一个值为0的节点
            if (l1.next != null || l2.next != null) {
                result.next = new ListNode(0);
                result = result.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        // 遍历未遍历完的链表的剩余部分
        result = singePlus(l1, result);
        result = singePlus(l2,result);

        // 判断最后是否还有进位1
        if (isOver) {
            result.next = new ListNode(1);
        }

        return resultRoot;
    }

    private ListNode singePlus(ListNode l, ListNode result) {
        while (l != null) {
            int tempResult = 0;
            if (isOver) {
                tempResult = l.val + 1;
                isOver = false;
            } else {
                tempResult = l.val;
            }
            if (tempResult >= 10) {
                tempResult -= 10;
                isOver = true;
            }
            result.val = tempResult;
            if (l.next != null) {
                result.next = new ListNode(0);
                result = result.next;
            }
            l = l.next;
        }
        return result;
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = stringToListNode(line);
            line = in.readLine();
            ListNode l2 = stringToListNode(line);

            ListNode ret = new Solution().addTwoNumbers(l1, l2);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}
package easy.topic7;

/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        if (p1 == null) {
            return l2;
        }
        if (p2 == null) {
            return l1;
        }
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                ListNode newNode = new ListNode(p2.val);
                p.next = newNode;
                p = p.next;
                p2 = p2.next;
            } else {
                ListNode newNode = new ListNode(p1.val);
                p.next = newNode;
                p = p.next;
                p1 = p1.next;
            }
        }
        while (p1 != null) {
            ListNode newNode = new ListNode(p1.val);
            p.next = newNode;
            p = p.next;
            p1 = p1.next;
        }
        while (p2 != null) {
            ListNode newNode = new ListNode(p2.val);
            p.next = newNode;
            p = p.next;
            p2 = p2.next;
        }
        return head.next;
    }
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

            ListNode ret = new Solution().mergeTwoLists(l1, l2);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}
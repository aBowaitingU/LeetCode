package hard.topic3;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;
import java.util.stream.Stream;

public class MainClass {


    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            if (lists.length == 1) {
                return lists[0];
            }

            ListNode result = null;
            ListNode p = null;
            while (true) {
                int minVal = Integer.MAX_VALUE;
                int minIndex = lists.length;
                for (int i = 0; i < lists.length; i++) {
                    if (lists[i] != null && lists[i].val < minVal) {
                        minIndex = i;
                        minVal = lists[i].val;
                    }
                }
                if (minIndex == lists.length) {
                    break;
                }

                if (result == null) {
                    result = new ListNode(minVal);
                    p = result;
                } else {
                    p.next = new ListNode(minVal);
                    p = p.next;
                }
                if (lists[minIndex].next != null) {
                    lists[minIndex] = lists[minIndex].next;
                } else {
                    lists[minIndex] = null;
                }

            }

            return result;
        }
    }
}

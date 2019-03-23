package site.wzhe.algorithms;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2019/3/4.
 */
public class ListTypeQuestions {

    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 题目1：给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * e.g：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode solution01(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode result = null, preNode = null, temp = null;
        int carryFlag = 0;
        while (!(l1 == null && l2 == null)) {
            int v1 = 0, v2 = 0;
            if (l1 != null) {
                v1 = l1.val;
            }
            if (l2 != null) {
                v2 = l2.val;
            }
            if ((carryFlag != 0) || (l1 != null && l2 != null)) {
                temp = new ListNode((v1 + v2 + carryFlag) % 10);
                carryFlag = (v1 + v2 + carryFlag >= 10) ? 1 : 0;
            } else {
                if (l1 == null) {
                    temp = l2;
                }
                if (l2 == null) {
                    temp = l1;
                }
            }
            if (preNode == null) {
                preNode = temp;
                result = preNode;
            } else {
                preNode.next = temp;
                preNode = temp;
            }
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }
        temp = (carryFlag != 0) ? new ListNode(carryFlag) : null;
        if (temp != null) {
            temp.next = null;
            preNode.next = temp;
        } else {
            preNode.next = null;
        }
        return result;
    }
}

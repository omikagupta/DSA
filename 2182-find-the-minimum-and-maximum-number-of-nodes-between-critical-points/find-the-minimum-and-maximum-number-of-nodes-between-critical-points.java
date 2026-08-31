/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstCriticalIndex = -1;
        int lastCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1;

        while (curr.next != null) {
            boolean isLocalMaxima = curr.val > prev.val && curr.val > curr.next.val;
            boolean isLocalMinima = curr.val < prev.val && curr.val < curr.next.val;

            if (isLocalMaxima || isLocalMinima) {
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - lastCriticalIndex);
                }
                lastCriticalIndex = currentIndex;
            }

            prev = curr;
            curr = curr.next;
            currentIndex++;
        }

        // Return [-1, -1] if fewer than two critical points are found
        if (firstCriticalIndex == -1 || firstCriticalIndex == lastCriticalIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = lastCriticalIndex - firstCriticalIndex;
        return new int[]{minDistance, maxDistance};
    }
}
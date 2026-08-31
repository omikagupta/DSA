/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    vector<int> nodesBetweenCriticalPoints(ListNode* head) {
        if (!head || !head->next || !head->next->next) {
            return {-1, -1};
        }

        int firstCriticalIndex = -1;
        int lastCriticalIndex = -1;
        int minDistance = INT_MAX;

        ListNode* prev = head;
        ListNode* curr = head->next;
        int currentIndex = 1;

        while (curr->next != nullptr) {
            bool isLocalMaxima = curr->val > prev->val && curr->val > curr->next->val;
            bool isLocalMinima = curr->val < prev->val && curr->val < curr->next->val;

            if (isLocalMaxima || isLocalMinima) {
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    minDistance = min(minDistance, currentIndex - lastCriticalIndex);
                }
                lastCriticalIndex = currentIndex;
            }

            prev = curr;
            curr = curr->next;
            currentIndex++;
        }

        // Fewer than two critical points found
        if (firstCriticalIndex == -1 || firstCriticalIndex == lastCriticalIndex) {
            return {-1, -1};
        }

        int maxDistance = lastCriticalIndex - firstCriticalIndex;
        return {minDistance, maxDistance};
    }
};
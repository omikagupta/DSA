#include <vector>
#include <algorithm>

class Solution {
public:
    int firstStableIndex(std::vector<int>& nums, int k) {
        int n = nums.size();
        
        // suffMin[i] stores the minimum value in nums[i..n-1]
        std::vector<int> suffMin(n);
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suffMin[i] = std::min(nums[i], suffMin[i + 1]);
        }
        
        // Traverse from left to right, maintaining running prefix maximum
        int prefMax = nums[0];
        for (int i = 0; i < n; ++i) {
            prefMax = std::max(prefMax, nums[i]);
            if (prefMax - suffMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
};
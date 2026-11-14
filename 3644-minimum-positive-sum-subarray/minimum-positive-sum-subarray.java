import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int minSum = Integer.MAX_VALUE;
        int n = nums.size();
        
        // Iterate over all possible subarray lengths from l to r
        for (int len = l; len <= r; len++) {
            // Compute the sum of the first window of size 'len'
            int currentSum = 0;
            for (int i = 0; i < len; i++) {
                currentSum += nums.get(i);
            }
            
            // Check if the first window sum is positive
            if (currentSum > 0) {
                minSum = Math.min(minSum, currentSum);
            }
            
            // Slide the window across the rest of the array
            for (int i = len; i < n; i++) {
                currentSum += nums.get(i) - nums.get(i - len);
                if (currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }
        
        // If minSum wasn't updated, return -1
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}
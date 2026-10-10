class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDelta = 0;
        long totalDiff = 0;
        
        // Count frequencies of each absolute difference
        int[] freq = new int[100001];
        for (int i = 0; i < n; i++) {
            int delta = Math.abs(nums1[i] - nums2[i]);
            freq[delta]++;
            maxDelta = Math.max(maxDelta, delta);
            totalDiff += delta;
        }
        
        long totalOperations = (long) k1 + k2;
        
        // If we can reduce all differences to 0, the minimum sum of squared difference is 0
        if (totalOperations >= totalDiff) {
            return 0;
        }
        
        // Greedily reduce the largest differences first
        for (int delta = maxDelta; delta > 0; delta--) {
            if (freq[delta] > 0) {
                long take = Math.min(totalOperations, freq[delta]);
                freq[delta] -= take;
                freq[delta - 1] += (int) take;
                totalOperations -= take;
                
                if (totalOperations == 0) {
                    break;
                }
            }
        }
        
        // Calculate the final sum of squared differences
        long minSumSquares = 0;
        for (int delta = 1; delta <= maxDelta; delta++) {
            if (freq[delta] > 0) {
                minSumSquares += (long) freq[delta] * delta * delta;
            }
        }
        
        return minSumSquares;
    }
}
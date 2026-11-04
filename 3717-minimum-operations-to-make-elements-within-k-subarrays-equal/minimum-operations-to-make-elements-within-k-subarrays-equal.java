import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];
        
        // Sliding window median tracker using TreeMaps
        MedianTracker tracker = new MedianTracker();
        for (int i = 0; i < x; i++) {
            tracker.add(nums[i]);
        }
        cost[0] = tracker.getCost();
        
        for (int i = x; i < n; i++) {
            tracker.remove(nums[i - x]);
            tracker.add(nums[i]);
            cost[i - x + 1] = tracker.getCost();
        }
        
        // DP array of size n + 1 to safely store costs up to index n
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0;
        
        for (int j = 1; j <= k; j++) {
            long[] nextDp = new long[n + 1];
            Arrays.fill(nextDp, Long.MAX_VALUE / 2);
            
            long minPrev = Long.MAX_VALUE / 2;
            for (int i = j * x; i <= n; i++) {
                minPrev = Math.min(minPrev, dp[i - x]);
                nextDp[i] = Math.min(nextDp[i - 1], minPrev + cost[i - x]);
            }
            dp = nextDp;
        }
        
        return dp[n];
    }
    
    class MedianTracker {
        private final TreeMap<Long, Integer> left = new TreeMap<>();
        private final TreeMap<Long, Integer> right = new TreeMap<>();
        private int leftSize = 0;
        private int rightSize = 0;
        private long sumLeft = 0;
        private long sumRight = 0;
        
        public void add(long num) {
            if (left.isEmpty() || num <= left.lastKey()) {
                left.put(num, left.getOrDefault(num, 0) + 1);
                sumLeft += num;
                leftSize++;
            } else {
                right.put(num, right.getOrDefault(num, 0) + 1);
                sumRight += num;
                rightSize++;
            }
            balance();
        }
        
        public void remove(long num) {
            if (left.containsKey(num)) {
                left.put(num, left.get(num) - 1);
                if (left.get(num) == 0) left.remove(num);
                sumLeft -= num;
                leftSize--;
            } else if (right.containsKey(num)) {
                right.put(num, right.get(num) - 1);
                if (right.get(num) == 0) right.remove(num);
                sumRight -= num;
                rightSize--;
            }
            balance();
        }
        
        private void balance() {
            while (leftSize > rightSize + 1) {
                long val = left.lastKey();
                removeMap(left, val);
                sumLeft -= val;
                leftSize--;
                
                right.put(val, right.getOrDefault(val, 0) + 1);
                sumRight += val;
                rightSize++;
            }
            while (rightSize > leftSize) {
                long val = right.firstKey();
                removeMap(right, val);
                sumRight -= val;
                rightSize--;
                
                left.put(val, left.getOrDefault(val, 0) + 1);
                sumLeft += val;
                leftSize++;
            }
        }
        
        private void removeMap(TreeMap<Long, Integer> map, long val) {
            map.put(val, map.get(val) - 1);
            if (map.get(val) == 0) map.remove(val);
        }
        
        public long getCost() {
            long median = left.lastKey();
            return (sumRight - rightSize * median) + (leftSize * median - sumLeft);
        }
    }
}
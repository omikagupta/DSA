class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        int len = 0;

        for (int x : nums) {

            int left = 0;
            int right = len;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (dp[mid] < x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            dp[left] = x;

            if (left == len) {
                len++;
            }
        }

        return len;
    }
}
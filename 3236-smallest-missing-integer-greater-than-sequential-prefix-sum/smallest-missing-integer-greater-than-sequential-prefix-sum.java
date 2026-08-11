class Solution {
    public int missingInteger(int[] nums) {

        int sum = nums[0];

        // Find the sum of the longest sequential prefix
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Find the smallest integer >= sum that is not in nums
        int ans = sum;

        while (true) {
            boolean found = false;

            for (int num : nums) {
                if (num == ans) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return ans;
            }

            ans++;
        }
    }
}
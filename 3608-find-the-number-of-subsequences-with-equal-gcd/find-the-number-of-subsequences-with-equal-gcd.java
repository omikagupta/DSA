class Solution {

    private static final int MOD = 1_000_000_007;
    private int[] nums;
    private int n;
    private Integer[][][] memo;

    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        n = nums.length;

        int max = 0;
        for (int x : nums)
            max = Math.max(max, x);

        memo = new Integer[n + 1][max + 1][max + 1];

        return dfs(0, 0, 0);
    }

    private int dfs(int idx, int g1, int g2) {

        if (idx == n) {
            return (g1 > 0 && g2 > 0 && g1 == g2) ? 1 : 0;
        }

        if (memo[idx][g1][g2] != null)
            return memo[idx][g1][g2];

        int x = nums[idx];

        long ans = 0;

        // Ignore current element
        ans += dfs(idx + 1, g1, g2);

        // Put in first subsequence
        int ng1 = (g1 == 0) ? x : gcd(g1, x);
        ans += dfs(idx + 1, ng1, g2);

        // Put in second subsequence
        int ng2 = (g2 == 0) ? x : gcd(g2, x);
        ans += dfs(idx + 1, g1, ng2);

        memo[idx][g1][g2] = (int) (ans % MOD);
        return memo[idx][g1][g2];
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
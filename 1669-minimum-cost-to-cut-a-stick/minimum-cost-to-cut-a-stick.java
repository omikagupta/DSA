import java.util.*;

class Solution {
    public int minCost(int n, int[] cuts) {

        int m = cuts.length;

        int[] arr = new int[m + 2];

        arr[0] = 0;
        arr[m + 1] = n;

        for (int i = 0; i < m; i++) {
            arr[i + 1] = cuts[i];
        }

        Arrays.sort(arr);

        int[][] dp = new int[m + 2][m + 2];

        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }

        return helper(arr, 0, m + 1, dp);
    }

    public int helper(int[] arr, int i, int j, int[][] dp) {

        if (i + 1 == j) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int ans = Integer.MAX_VALUE;

        for (int k = i + 1; k <= j - 1; k++) {

            int left = helper(arr, i, k, dp);
            int right = helper(arr, k, j, dp);

            int cost = arr[j] - arr[i];

            ans = Math.min(ans, left + right + cost);
        }

        dp[i][j] = ans;

        return dp[i][j];
    }
}
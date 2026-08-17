class Solution {
public:
    int dp[500][500];
    int prefix[501];

    int solve(vector<int>& stoneValue, int l, int r) {

        if (l == r)
            return 0;

        if (dp[l][r] != -1)
            return dp[l][r];

        int ans = 0;

        for (int k = l; k < r; k++) {

            int leftSum = prefix[k + 1] - prefix[l];
            int rightSum = prefix[r + 1] - prefix[k + 1];

            if (leftSum < rightSum) {
                ans = max(ans,
                         leftSum + solve(stoneValue, l, k));
            }

            else if (rightSum < leftSum) {
                ans = max(ans,
                         rightSum + solve(stoneValue, k + 1, r));
            }

            else {
                ans = max(ans,
                         leftSum + max(
                             solve(stoneValue, l, k),
                             solve(stoneValue, k + 1, r)
                         ));
            }
        }

        return dp[l][r] = ans;
    }

    int stoneGameV(vector<int>& stoneValue) {

        int n = stoneValue.size();

        memset(dp, -1, sizeof(dp));

        prefix[0] = 0;

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(stoneValue, 0, n - 1);
    }
};
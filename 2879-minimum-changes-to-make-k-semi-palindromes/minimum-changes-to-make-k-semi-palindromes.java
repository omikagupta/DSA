class Solution {
    // Memoization table for substring semi-palindrome costs
    private Integer[][] memoCost;
    private int[][] minCost;
    private int n;

    public int minimumChanges(String s, int k) {
        this.n = s.length();
        this.minCost = new int[n][n];

        // Step 1: Precompute the minimum changes to make s[i...j] a semi-palindrome
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                minCost[i][j] = getMinCost(s, i, j);
            }
        }

        // Step 2: DP to partition the string into k semi-palindromes
        // dp[i][parts] = min cost to partition s[i...n-1] into `parts` semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(dp[i], 1000000);
        }
        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = 0; i < n; i++) {
                // Try all possible split points `x` for the current part s[i...x]
                for (int x = i; x < n - (k - parts); x++) {
                    int len = x - i + 1;
                    // A semi-palindrome must have a length of at least 2 because its divisor d < length
                    if (len < 2 && parts < k - (n - 1 - x)) continue; 
                    if (dp[i][parts - 1] != 1000000) {
                        dp[x + 1][parts] = Math.min(dp[x + 1][parts], dp[i][parts - 1] + minCost[i][x]);
                    }
                }
            }
        }

        // Wait, let's structure the DP properly from left to right:
        // Let's use a standard 1D/2D DP where dp[i][parts] means dividing prefix s[0...i] into parts
        return solveTabulation(s, k);
    }

    private int solveTabulation(String s, int k) {
        int len = s.length();
        // dp[i][p] -> min changes to partition prefix of length i into p parts
        int[][] dp = new int[len + 1][k + 1];
        for (int i = 0; i <= len; i++) {
            java.util.Arrays.fill(dp[i], 1_000_000);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= len; i++) {
            for (int p = 1; p <= Math.min(i, k); p++) {
                // Try ending the p-th semi-palindrome from index j to i-1
                for (int j = p - 1; j < i; j++) {
                    if (i - j < 2) continue; // semi-palindrome length must be >= 2
                    dp[i][p] = Math.min(dp[i][p], dp[j][p - 1] + minCost[j][i - 1]);
                }
            }
        }
        return dp[len][k];
    }

    // Helper to calculate minimum changes to make s[i...j] a semi-palindrome
    private int getMinCost(String s, int i, int j) {
        int length = j - i + 1;
        int minChanges = 1_000_000;

        // Find all proper divisors `d` of `length`
        for (int d = 1; d < length; d++) {
            if (length % d == 0) {
                int currentChanges = 0;
                // Check each group defined by remainder modulo d
                for (int remainder = 0; remainder < d; remainder++) {
                    // Collect characters in this group and count changes to make it a palindrome
                    int left = i + remainder;
                    int right = i + length - (d - remainder); // last element in group
                    while (left < right) {
                        if (s.charAt(left) != s.charAt(right)) {
                            currentChanges++;
                        }
                        left += d;
                        right -= d;
                    }
                }
                minChanges = Math.min(minChanges, currentChanges);
            }
        }
        return minChanges;
    }
}
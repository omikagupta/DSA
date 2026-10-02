class Solution {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

        int n = maxChoosableInteger;

        // If even using all numbers cannot reach desiredTotal
        int total = n * (n + 1) / 2;

        if (total < desiredTotal) {
            return false;
        }

        // dp[mask] = can the current player force a win?
        boolean[] dp = new boolean[1 << n];

        // Calculate states from more-used numbers to fewer-used numbers
        for (int mask = (1 << n) - 1; mask >= 0; mask--) {

            // Find current sum from the mask
            int currentSum = 0;

            for (int i = 0; i < n; i++) {

                // Is number (i + 1) already used?
                if ((mask & (1 << i)) != 0) {
                    currentSum += i + 1;
                }
            }

            // Try every unused number
            for (int i = 0; i < n; i++) {

                // If already used, skip
                if ((mask & (1 << i)) != 0) {
                    continue;
                }

                int number = i + 1;

                // I reach the target → I win immediately
                if (currentSum + number >= desiredTotal) {
                    dp[mask] = true;
                    break;
                }

                // Mark this number as used
                int newMask = mask | (1 << i);

                // Now it is opponent's turn.
                // If opponent cannot win, I can win.
                if (!dp[newMask]) {
                    dp[mask] = true;
                    break;
                }
            }
        }

        // Game starts with no numbers used
        return dp[0];
    }
}
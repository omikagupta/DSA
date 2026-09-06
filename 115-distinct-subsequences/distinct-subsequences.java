class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // If target is longer than source, impossible to form t
        if (m < n) return 0;

        // dp[j] stores the number of subsequences of s that form t[0...j-1]
        // Using long or double during calculation prevents 32-bit integer overflow on intermediate steps
        double[] dp = new double[n + 1];
        dp[0] = 1; // An empty string t can always be formed 1 way

        for (int i = 0; i < m; i++) {
            char sc = s.charAt(i);
            // Traverse backwards to use previous row values
            for (int j = n - 1; j >= 0; j--) {
                if (sc == t.charAt(j)) {
                    dp[j + 1] += dp[j];
                }
            }
        }

        return (int) dp[n];
    }
}
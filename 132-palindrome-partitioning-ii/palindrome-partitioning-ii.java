class Solution {
    public int minCut(String s) {
        int n = s.length();

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        return helper(s, dp, 0);
    }

    public int helper(String s, int[] dp, int i) {
        int n = s.length();

        if (i == n) {
            return 0;
        }

        if (isPalindrome(s, i, n - 1)) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int answer = Integer.MAX_VALUE;

        for (int k = i; k < n - 1; k++) {

            if (isPalindrome(s, i, k)) {

                int cuts = 1 + helper(s, dp, k + 1);

                answer = Math.min(answer, cuts);
            }
        }

        return dp[i] = answer;
    }

    public boolean isPalindrome(String s, int i, int j) {

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}
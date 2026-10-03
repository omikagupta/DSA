class Solution {
    public int numDecodings(String s) {
        int n = s.length();

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        return helper(s, dp, 0);
    }

    public int helper(String s, int[] dp, int i) {
        int n = s.length();

       
        if (i == n) {
            return 1;
        }


        if (s.charAt(i) == '0') {
            return 0;
        }

  
        if (dp[i] != -1) {
            return dp[i];
        }

      
        int one = helper(s, dp, i + 1);

        int two = 0;

        if (i + 1 < n) {
            int num = (s.charAt(i) - '0') * 10
                    + (s.charAt(i + 1) - '0');

            if (num >= 10 && num <= 26) {
                two = helper(s, dp, i + 2);
            }
        }

        dp[i] = one + two;

        return dp[i];
    }
}
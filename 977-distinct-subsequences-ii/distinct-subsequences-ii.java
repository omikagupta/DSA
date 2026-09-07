class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        // end[c] stores the count of distinct subsequences ending with character ('a' + c)
        int[] end = new int[26];
        
        for (char ch : s.toCharArray()) {
            int c = ch - 'a';
            // Current character can append to all existing distinct subsequences,
            // plus form a subsequence of length 1 on its own (+1).
            long sum = 1;
            for (int count : end) {
                sum = (sum + count) % MOD;
            }
            end[c] = (int) sum;
        }
        
        // Sum all subsequences ending with any character
        long ans = 0;
        for (int count : end) {
            ans = (ans + count) % MOD;
        }
        
        return (int) ans;
    }
}
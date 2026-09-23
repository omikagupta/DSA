import java.util.*;

class Solution {
    public int minDistance(String word1, String word2) {

        int[][] dp = new int[word1.length()][word2.length()];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper(word1, word2, 0, 0, dp);
    }

    public int helper(String word1, String word2,
                      int i, int j, int[][] dp) {

        // word1 exhausted → insert remaining characters of word2
        if (i == word1.length()) {
            return word2.length() - j;
        }

        // word2 exhausted → delete remaining characters of word1
        if (j == word2.length()) {
            return word1.length() - i;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // Characters already match
        if (word1.charAt(i) == word2.charAt(j)) {

            dp[i][j] = helper(word1, word2, i + 1, j + 1, dp);

        } else {

       
            int insert = helper(word1, word2, i, j + 1, dp);

           
            int delete = helper(word1, word2, i + 1, j, dp);

            int replace = helper(word1, word2, i + 1, j + 1, dp);

            dp[i][j] = 1 + Math.min(insert,
                            Math.min(delete, replace));
        }

        return dp[i][j];
    }
}
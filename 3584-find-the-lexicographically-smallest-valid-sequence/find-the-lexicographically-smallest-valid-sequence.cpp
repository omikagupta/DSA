class Solution {
public:
    vector<int> validSequence(string word1, string word2) {
        int n = word1.size();
        int m = word2.size();

        // suf[i] = maximum number of characters
        // of word2 that can be matched from word1[i...]
        vector<int> suf(n + 1, 0);

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            suf[i] = suf[i + 1];

            if (j >= 0 && word1[i] == word2[j]) {
                suf[i]++;
                j--;
            }
        }

        vector<int> ans(m);

        int i = 0;
        j = 0;

        // Find the first mismatch where we use our one change
        while (i < n && j < m) {

            if (word1[i] == word2[j]) {
                ans[j] = i;
                j++;
            }
            else {
                // Change word1[i] -> word2[j]
                if (suf[i + 1] >= m - j - 1) {
                    ans[j] = i;
                    j++;

                    i++;

                    break;
                }
            }

            i++;
        }

        // Could not finish
        if (j < m && i == n) {
            return {};
        }

        // After using the modification,
        // remaining characters must match exactly
        while (i < n && j < m) {

            if (word1[i] == word2[j]) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        if (j < m) {
            return {};
        }

        return ans;
    }
};
class Solution {
public:
    int distinctSubseqII(string s) {
        const int MOD = 1e9 + 7;
        vector<int> lastAdded(26, 0);
        long long total = 0; // Total distinct non-empty subsequences so far

        for (char ch : s) {
            int idx = ch - 'a';
            // Distinct subsequences created ending in ch: (total + 1) - lastAdded[idx]
            long long added = (total + 1 - lastAdded[idx] + MOD) % MOD;

            total = (total + added) % MOD;
            lastAdded[idx] = (lastAdded[idx] + added) % MOD;
        }

        return total;
    }
};
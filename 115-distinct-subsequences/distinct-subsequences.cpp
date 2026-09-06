#include <string>
#include <vector>

class Solution {
public:
    int numDistinct(std::string s, std::string t) {
        int m = s.length();
        int n = t.length();

        if (m < n) return 0;

        // dp[j] stores the number of subsequences of s that form t[0...j-1]
        // Using unsigned long long or double prevents 32-bit integer overflow during intermediate steps
        std::vector<unsigned long long> dp(n + 1, 0);
        dp[0] = 1; // An empty string can always be formed 1 way

        for (int i = 0; i < m; ++i) {
            char sc = s[i];
            // Traverse backwards to avoid overwriting values from the previous step
            for (int j = n - 1; j >= 0; --j) {
                if (sc == t[j]) {
                    dp[j + 1] += dp[j];
                }
            }
        }

        return static_cast<int>(dp[n]);
    }
};
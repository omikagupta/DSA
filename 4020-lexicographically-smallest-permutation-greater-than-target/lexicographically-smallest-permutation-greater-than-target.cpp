class Solution {
public:
    string lexGreaterPermutation(string s, string target) {

        int n = s.size();

        // frequency(s) - frequency(target)
        vector<int> count(26, 0);

        for (int i = 0; i < n; i++) {
            count[s[i] - 'a']++;
            count[target[i] - 'a']--;
        }

        // Try changing target from right to left
        for (int i = n - 1; i >= 0; i--) {

            int cur = target[i] - 'a';

            // Restore target[i]
            count[cur]++;

            // Check if target[0 ... i-1]
            // can be formed
            bool possible = true;

            for (int c = 0; c < 26; c++) {
                if (count[c] < 0) {
                    possible = false;
                    break;
                }
            }

            if (!possible)
                continue;

            // Smallest character greater than target[i]
            for (int c = cur + 1; c < 26; c++) {

                if (count[c] > 0) {

                    count[c]--;

                    string ans = target.substr(0, i);

                    // Make current position greater
                    ans += char('a' + c);

                    // Remaining characters in sorted order
                    for (int k = 0; k < 26; k++) {
                        while (count[k] > 0) {
                            ans += char('a' + k);
                            count[k]--;
                        }
                    }

                    return ans;
                }
            }
        }

        return "";
    }
};
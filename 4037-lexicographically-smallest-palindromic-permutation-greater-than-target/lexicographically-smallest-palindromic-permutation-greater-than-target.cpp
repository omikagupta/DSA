#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    string lexPalindromicPermutation(string s, string target) {
        int n = s.length();
        vector<int> count(26, 0);
        for (char c : s) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        char midChar = 0;
        vector<int> halfCount(26, 0);

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char)('a' + i);
            }
            halfCount[i] = count[i] / 2;
        }

        // A valid palindrome cannot be formed
        if ((n % 2 == 0 && oddCount > 0) || (n % 2 != 0 && oddCount != 1)) {
            return "";
        }

        int m = n / 2;
        string best = "";

        // Case 1: First half shares a prefix of length i with target[0..i-1],
        // and at index i, firstHalf[i] > target[i].
        for (int i = m - 1; i >= 0; i--) {
            vector<int> curHalf = halfCount;
            bool prefixValid = true;
            string prefix(m, ' ');

            // Build matching prefix target[0 .. i-1]
            for (int j = 0; j < i; j++) {
                int c = target[j] - 'a';
                if (curHalf[c] > 0) {
                    curHalf[c]--;
                    prefix[j] = target[j];
                } else {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) continue;

            // Try the smallest available char strictly greater than target[i]
            int targetCharVal = target[i] - 'a';
            for (int c = targetCharVal + 1; c < 26; c++) {
                if (curHalf[c] > 0) {
                    prefix[i] = (char)('a' + c);
                    curHalf[c]--;

                    // Fill remainder greedily with smallest available characters
                    int fillIdx = i + 1;
                    for (int ch = 0; ch < 26; ch++) {
                        while (curHalf[ch] > 0) {
                            prefix[fillIdx++] = (char)('a' + ch);
                            curHalf[ch]--;
                        }
                    }

                    string candidate = buildFullPalindrome(prefix, midChar, n);
                    if (candidate > target) {
                        if (best.empty() || candidate < best) {
                            best = candidate;
                        }
                    }
                    break; // Found the minimal valid character for this prefix length
                }
            }
        }

        // Case 2: First half exactly matches target[0 .. m-1]
        vector<int> curHalf = halfCount;
        string prefix(m, ' ');
        bool canMatchFirstHalf = true;

        for (int j = 0; j < m; j++) {
            int c = target[j] - 'a';
            if (curHalf[c] > 0) {
                curHalf[c]--;
                prefix[j] = target[j];
            } else {
                canMatchFirstHalf = false;
                break;
            }
        }

        if (canMatchFirstHalf) {
            string candidate = buildFullPalindrome(prefix, midChar, n);
            if (candidate > target) {
                if (best.empty() || candidate < best) {
                    best = candidate;
                }
            }
        }

        return best;
    }

private:
    string buildFullPalindrome(const string& firstHalf, char midChar, int n) {
        string res(n, ' ');
        int m = firstHalf.length();

        for (int i = 0; i < m; i++) {
            res[i] = firstHalf[i];
            res[n - 1 - i] = firstHalf[i];
        }

        if (n % 2 != 0) {
            res[m] = midChar;
        }

        return res;
    }
};
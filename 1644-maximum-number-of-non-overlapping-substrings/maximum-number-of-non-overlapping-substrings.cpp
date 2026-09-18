#include <vector>
#include <string>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<string> maxNumOfSubstrings(string s) {
        int n = s.length();
        vector<int> first(26, -1);
        vector<int> last(26, -1);

        // Step 1: Find first and last indices for each character
        for (int i = 0; i < n; i++) {
            int ch = s[i] - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        // Step 2: Expand ranges for each present character
        vector<pair<int, int>> ranges;
        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;

            int left = first[c];
            int right = last[c];
            bool valid = true;

            for (int i = left; i <= right; i++) {
                int ch = s[i] - 'a';
                if (first[ch] < left) {
                    valid = false; // Cannot start at 'left' without violating character completeness
                    break;
                }
                right = max(right, last[ch]);
            }

            if (valid) {
                ranges.push_back({left, right});
            }
        }

        // Step 3: Sort valid ranges by their right endpoint
        sort(ranges.begin(), ranges.end(), [](const pair<int, int>& a, const pair<int, int>& b) {
            return a.second < b.second;
        });

        // Step 4: Greedy selection of non-overlapping substrings
        vector<string> result;
        int lastEnd = -1;

        for (const auto& r : ranges) {
            int left = r.first;
            int right = r.second;
            if (left > lastEnd) {
                result.push_back(s.substr(left, right - left + 1));
                lastEnd = right;
            }
        }

        return result;
    }
};
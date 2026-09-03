#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

class Solution {
public:
    bool uniformArray(vector<int>& nums1) {
        int minOdd = INT_MAX;
        int minEven = INT_MAX;

        for (int x : nums1) {
            if (x % 2 != 0) {
                minOdd = min(minOdd, x);
            } else {
                minEven = min(minEven, x);
            }
        }

        // If array already contains only odds or only evens
        if (minOdd == INT_MAX || minEven == INT_MAX) {
            return true;
        }

        // All elements can only become odd if every even number is strictly greater than minOdd
        return minOdd < minEven;
    }
};
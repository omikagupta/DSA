class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {

        unordered_set<int> st;

        // Store all elements
        for (int num : nums) {
            st.insert(num);
        }

        // Check k, 2k, 3k, ...
        int multiple = k;

        while (st.count(multiple)) {
            multiple += k;
        }

        return multiple;
    }
};
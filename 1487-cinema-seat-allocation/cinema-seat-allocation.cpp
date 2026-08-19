#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxNumberOfFamilies(int n, vector<vector<int>>& reservedSeats) {

        unordered_map<int,int> mp;

        // Store reserved seats row wise
        for(auto &seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            mp[row] |= (1 << (col - 1));
        }

        int ans = (n - mp.size()) * 2;

        for(auto &it : mp) {

            int mask = it.second;
            int families = 0;

            // Seats 2-5
            if((mask & 0b0000011110) == 0)
                families++;

            // Seats 6-9
            if((mask & 0b0111100000) == 0)
                families++;

            // Seats 4-7
            if(families == 0 && (mask & 0b0001111000) == 0)
                families++;

            ans += families;
        }

        return ans;
    }
};
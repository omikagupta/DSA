#include <vector>

class Solution {
public:
    int totalNumbers(std::vector<int>& digits) {
        int count[10] = {0};
        for (int d : digits) {
            count[d]++;
        }

        int total = 0;
        // Check all 3-digit even numbers from 100 to 998
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;
            int d2 = (num / 10) % 10;
            int d3 = num % 10;

            int needed[10] = {0};
            needed[d1]++;
            needed[d2]++;
            needed[d3]++;

            if (needed[d1] <= count[d1] && 
                needed[d2] <= count[d2] && 
                needed[d3] <= count[d3]) {
                total++;
            }
        }

        return total;
    }
};
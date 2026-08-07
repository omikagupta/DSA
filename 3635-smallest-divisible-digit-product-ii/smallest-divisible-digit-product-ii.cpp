#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    string smallestNumber(string num, long long t) {
        // Factorize t into prime factors 2, 3, 5, 7
        long long temp = t;
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        
        while (temp % 2 == 0) { temp /= 2; c2++; }
        while (temp % 3 == 0) { temp /= 3; c3++; }
        while (temp % 5 == 0) { temp /= 5; c5++; }
        while (temp % 7 == 0) { temp /= 7; c7++; }

        // If t has prime factors greater than 7, no digit product can be divisible by t
        if (temp > 1) return "-1";

        int n = num.length();

        // Calculate prefix factor counts for num
        vector<int> p2(n + 1, 0), p3(n + 1, 0), p5(n + 1, 0), p7(n + 1, 0);
        int zeroIdx = -1;

        for (int i = 0; i < n; i++) {
            int d = num[i] - '0';
            if (d == 0) {
                zeroIdx = i;
                break;
            }
            p2[i + 1] = p2[i] + getFactor(d, 2);
            p3[i + 1] = p3[i] + getFactor(d, 3);
            p5[i + 1] = p5[i] + getFactor(d, 5);
            p7[i + 1] = p7[i] + getFactor(d, 7);
        }

        // Check if num itself (if zero-free) is already divisible
        if (zeroIdx == -1 && p2[n] >= c2 && p3[n] >= c3 && p5[n] >= c5 && p7[n] >= c7) {
            return num;
        }

        // Try to keep prefix of length i and increase digit at position i
        int limit = (zeroIdx == -1) ? n - 1 : zeroIdx;
        for (int i = limit; i >= 0; i--) {
            int startDigit = num[i] - '0' + 1;
            for (int d = startDigit; d <= 9; d++) {
                int rem2 = c2 - p2[i] - getFactor(d, 2);
                int rem3 = c3 - p3[i] - getFactor(d, 3);
                int rem5 = c5 - p5[i] - getFactor(d, 5);
                int rem7 = c7 - p7[i] - getFactor(d, 7);

                int remLen = n - 1 - i;
                if (getMinLen(rem2, rem3, rem5, rem7) <= remLen) {
                    string res = num.substr(0, i);
                    res += to_string(d);
                    fillSuffix(res, remLen, rem2, rem3, rem5, rem7);
                    return res;
                }
            }
        }

        // If no number of length n works, construct smallest valid number with length > n
        int minLen = max(n + 1, getMinLen(c2, c3, c5, c7));
        string res = "";
        fillSuffix(res, minLen, c2, c3, c5, c7);
        return res;
    }

private:
    void fillSuffix(string &res, int len, int r2, int r3, int r5, int r7) {
        for (int pos = 0; pos < len; pos++) {
            int remPos = len - 1 - pos;
            for (int d = 1; d <= 9; d++) {
                int nr2 = r2 - getFactor(d, 2);
                int nr3 = r3 - getFactor(d, 3);
                int nr5 = r5 - getFactor(d, 5);
                int nr7 = r7 - getFactor(d, 7);

                if (getMinLen(nr2, nr3, nr5, nr7) <= remPos) {
                    res += (char)('0' + d);
                    r2 = nr2;
                    r3 = nr3;
                    r5 = nr5;
                    r7 = nr7;
                    break;
                }
            }
        }
    }

    int getMinLen(int r2, int r3, int r5, int r7) {
        r2 = max(0, r2);
        r3 = max(0, r3);
        r5 = max(0, r5);
        r7 = max(0, r7);

        // Maximize digits 9, 8, 7, 5
        int count = r7 + r5 + (r3 / 2) + (r2 / 3);
        int rem3 = r3 % 2;
        int rem2 = r2 % 3;

        // Combine remaining factors if possible
        if (rem3 == 1 && rem2 == 1) {
            count += 1; // Combined into '6'
        } else {
            count += rem3;
            if (rem2 > 0) count += 1;
        }

        return count;
    }

    int getFactor(int val, int p) {
        int count = 0;
        while (val > 0 && val % p == 0) {
            count++;
            val /= p;
        }
        return count;
    }
};
class Solution {
public:
    bool sumGame(string num) {
        int n = num.size();

        int leftSum = 0;
        int rightSum = 0;

        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < n / 2; i++) {
            if (num[i] == '?') {
                leftQ++;
            } else {
                leftSum += num[i] - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            if (num[i] == '?') {
                rightQ++;
            } else {
                rightSum += num[i] - '0';
            }
        }

        // Odd number of question marks
        if ((leftQ + rightQ) % 2 == 1) {
            return true;
        }

        int sumDiff = leftSum - rightSum;
        int questionDiff = rightQ - leftQ;

        // Bob can balance the game
        if (sumDiff == (questionDiff / 2) * 9) {
            return false;
        }

        return true;
    }
};
class Solution {
    public long countPairs(int[] nums, int k) {
        long answer = 0;

        int[] freq = new int[k + 1];

        for (int num : nums) {
            int g = gcd(num, k);

            for (int d = 1; d * d <= k; d++) {
                if (k % d == 0) {

              
                    if ((long) g * d % k == 0) {
                        answer += freq[d];
                    }

                   
                    if (d != k / d && (long) g * (k / d) % k == 0) {
                        answer += freq[k / d];
                    }
                }
            }

            freq[g]++;
        }

        return answer;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
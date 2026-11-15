class Solution {
    public int numOfSubarrays(int[] arr) {

        int even = 1;
        int odd = 0;

        int ans = 0;
        int sum = 0;

        int MOD = 1000000007;

        for (int i : arr) {

            sum += i;

            if (sum % 2 == 0) {

                ans = (ans + odd) % MOD;
                even++;

            } else {

                ans = (ans + even) % MOD;
                odd++;
            }
        }

        return ans;
    }
}
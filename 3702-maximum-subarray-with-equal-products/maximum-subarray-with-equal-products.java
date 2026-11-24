class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1; // Any single element subarray is trivially valid (x == x * x is false, wait: prod=x, gcd=x, lcm=x -> x == x*x only for 1, but constraints guarantee a valid subarray of length >= 2 exists)
        
        // Maximum possible lcm(1..10) * gcd(1..10) = 2520 * 10 = 25200
        long maxPossibleVal = 25200;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            long currentGcd = 0;
            long currentLcm = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];
                
                // Prevent overflow and prune impossible subarrays early
                if (prod > maxPossibleVal) {
                    break;
                }

                currentGcd = gcd(currentGcd, nums[j]);
                currentLcm = lcm(currentLcm, nums[j]);

                if (prod == currentLcm * currentGcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
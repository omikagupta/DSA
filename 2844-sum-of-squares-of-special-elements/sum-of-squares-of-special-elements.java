class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        // Iterate from 1 to n to handle 1-indexing
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                int val = nums[i - 1]; // convert 1-based index to 0-based array index
                sum += val * val;
            }
        }
        
        return sum;
    }
}
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        Arrays.sort(squares);
        
        long score = 0;
        int mid = n / 2;
        
        // Subtract the smaller half
        for (int i = 0; i < mid; i++) {
            score -= squares[i];
        }
        
        // Add the larger half
        for (int i = mid; i < n; i++) {
            score += squares[i];
        }
        
        return score;
    }
}
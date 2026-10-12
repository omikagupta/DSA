class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[30];
        
        // Step 1: Count set bits at each position across all numbers
        for (int num : nums) {
            for (int j = 0; j < 30; j++) {
                if ((num & (1 << j)) != 0) {
                    bitCount[j]++;
                }
            }
        }
        
        long MOD = 1_000_000_007;
        long totalSum = 0;
        
        // Step 2: Greedily form the top k numbers
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int j = 0; j < 30; j++) {
                if (bitCount[j] > 0) {
                    currentNum |= (1 << j);
                    bitCount[j]--;
                }
            }
            totalSum = (totalSum + (currentNum * currentNum) % MOD) % MOD;
        }
        
        return (int) totalSum;
    }
}
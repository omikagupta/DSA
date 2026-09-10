class Solution {
    public int rob(int[] nums) {
      int n = nums.length;

        if (n == 1) return nums[0];

     
        int case1 = helper(nums, 0, n - 2);

       
        int case2 = helper(nums, 1, n - 1);

        return Math.max(case1, case2);
    }
    
    public int helper(int[]nums,int start,int end){
        if(start == end) return nums[start];
        int prev2=nums[start];
        int prev1=Math.max(nums[start],nums[start+1]);
        for(int i=start+2;i<=end;i++){
            int current=Math.max(prev1,prev2+nums[i]);
        
        prev2=prev1;
        prev1=current;
        }
        return prev1;
    }
}
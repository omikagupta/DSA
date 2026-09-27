class Solution {
    public int[] productExceptSelf(int[] nums) {
      int[]ans = new int[nums.length]; 
      Arrays.fill(ans,1);
      int n=nums.length;
       int lp=1;
       int rp=1;
       for(int i =0;i<n;i++){
        ans[i]=lp*ans[i];
        lp=lp*nums[i];
       }
          for(int i=n-1;i>=0;i--){
        ans[i]=rp*ans[i];
        rp=rp*nums[i];
       }
       return ans;
    }
}
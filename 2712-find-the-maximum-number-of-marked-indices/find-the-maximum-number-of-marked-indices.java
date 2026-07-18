class Solution {
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
int cnt=0;
        int i=0;
        int j=(n+1)/2;
    while (i < n / 2 && j < n) {
if(2*nums[i]<=nums[j]){
    cnt++;
i++;
j++;
}
else{
j++;
}
      }
      return cnt*2;
    }
}
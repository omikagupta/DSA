class Solution {
    public int search(int[] nums, int target) {
        return binary_s(nums,0,nums.length-1,target);
    }
    public int binary_s(int[]nums,int left,int right,int target){
        if(left>right) return -1;
        int mid=left+(right-left)/2;
        if(nums[mid]==target) return mid;
if(nums[mid]>target) {
    return binary_s(nums,left,mid-1,target);
    }
 return binary_s(nums,mid+1,right,target);
    }
}
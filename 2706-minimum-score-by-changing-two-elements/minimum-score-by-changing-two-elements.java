import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        
        // Option 1: Change the two smallest elements
        int option1 = nums[n - 1] - nums[2];
        
        // Option 2: Change the two largest elements
        int option2 = nums[n - 3] - nums[0];
        
        // Option 3: Change one smallest and one largest element
        int option3 = nums[n - 2] - nums[1];
        
        return Math.min(option1, Math.min(option2, option3));
    }
}

class Solution {

    public boolean canPartitionKSubsets(int[] nums, int k) {

        int sum = 0;

        for (int n : nums) {
            sum += n;
        }

        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;

        Arrays.sort(nums);

        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        int[] buckets = new int[k];

        return backtrack(nums, 0, buckets, target);
    }

    boolean backtrack(
        int[] nums,
        int index,
        int[] buckets,
        int target
    ) {

        if (index == nums.length) {
            return true;
        }

        int num = nums[index];

        for (int i = 0; i < buckets.length; i++) {

            
            if (buckets[i] + num > target) {
                continue;
            }

   
            if (i > 0 && buckets[i] == 0 && buckets[i - 1] == 0) {
                continue;
            }

            buckets[i] += num;

         
            if (backtrack(nums, index + 1, buckets, target)) {
                return true;
            }

            buckets[i] -= num;

     
            if (buckets[i] == 0) {
                break;
            }
        }

        return false;
    }
}

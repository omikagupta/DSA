class Solution {
    public int searchInsert(int[] nums, int target) {
        return binary(nums, target, 0, nums.length - 1);
    }

    public int binary(int[] nums, int target, int left, int right) {
        if (left > right)
            return left;

        int mid = left + (right - left) / 2;

        if (nums[mid] == target)
            return mid;

        if (nums[mid] < target)
            return binary(nums, target, mid + 1, right);

        return binary(nums, target, left, mid - 1);
    }
}
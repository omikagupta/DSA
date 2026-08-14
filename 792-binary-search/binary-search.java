class Solution {
    public int search(int[] nums, int target) {
        return binary(nums, target, 0, nums.length - 1);
    }

    public int binary(int[] nums, int target, int left, int right) {
        if (left > right)
            return -1;

        int mid = left + (right - left) / 2;

        if (nums[mid] == target)
            return mid;

        if (nums[mid] < target)
            return binary(nums, target, mid + 1, right);

        return binary(nums, target, left, mid - 1);
    }
}
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> arr = new ArrayList<>();

    void allsubset(List<List<Integer>> ans, List<Integer> arr, int[] nums, int i) {

        if (i == nums.length) {
            ans.add(new ArrayList<>(arr));
            return;
        }

       
        arr.add(nums[i]);
        allsubset(ans, arr, nums, i + 1);
        arr.remove(arr.size() - 1);

        allsubset(ans, arr, nums, i + 1);
    }

    public List<List<Integer>> subsets(int[] nums) {
        allsubset(ans, arr, nums, 0);
        return ans;
    }
}
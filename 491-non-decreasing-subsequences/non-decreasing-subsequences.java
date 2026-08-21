class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        backtrack(ans, path, nums, 0);

        return ans;
    }

    void backtrack(List<List<Integer>> ans,
                   List<Integer> path,
                   int[] nums,
                   int start) {

        if (path.size() >= 2) {
            ans.add(new ArrayList<>(path));
        }

        Set<Integer> used = new HashSet<>();

        for (int i = start; i < nums.length; i++) {

            if (used.contains(nums[i])) {
                continue;
            }

            
            if (!path.isEmpty() &&
                nums[i] < path.get(path.size() - 1)) {
                continue;
            }

            used.add(nums[i]);

        
            path.add(nums[i]);

      
            backtrack(ans, path, nums, i + 1);

         
            path.remove(path.size() - 1);
        }
    }
}
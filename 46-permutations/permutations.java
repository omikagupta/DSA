class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        backtrack(ans, path, nums, visited);

        return ans;
    }

    public void backtrack(List<List<Integer>> ans,
                          List<Integer> path,
                          int[] nums,
                          boolean[] visited) {

        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int idx = 0; idx < nums.length; idx++) {

            if (!visited[idx]) {

            
                path.add(nums[idx]);
                visited[idx] = true;

           
                backtrack(ans, path, nums, visited);

          
                path.remove(path.size() - 1);
                visited[idx] = false;
            }
        }
    }
}
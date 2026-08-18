class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        backtrack(n, k, ans, path, 1);

        return ans;
    }

    public void backtrack(int n, int k, List<List<Integer>> ans,
                          List<Integer> path, int idx) {

        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }

        if (idx > n) {
            return;
        }

        path.add(idx);
        backtrack(n, k, ans, path, idx + 1);

        path.remove(path.size() - 1);

        backtrack(n, k, ans, path, idx + 1);
    }
}
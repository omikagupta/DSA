class Solution {

    int answer = 0;

    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root, 0, 0);
        dfs(root, 1, 0);

        return answer;
    }

    public void dfs(TreeNode node, int dir, int length) {

        if (node == null) {
            return;
        }

        answer = Math.max(answer, length);

        if (dir == 0) {
           
            dfs(node.left, 1, length + 1);

          
            dfs(node.right, 0, 1);

        } else {
            
            dfs(node.right, 0, length + 1);

          
            dfs(node.left, 1, 1);
        }
    }
}
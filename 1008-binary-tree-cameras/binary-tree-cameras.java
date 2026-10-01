class Solution {

    int cameras = 0;

    public int minCameraCover(TreeNode root) {
        int state = dfs(root);

        if (state == 2) {
            cameras++;
        }

        return cameras;
    }

  
    public int dfs(TreeNode node) {

       
        if (node == null) {
            return 1;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        if (left == 2 || right == 2) {
            cameras++;
            return 0;
        }

     
        if (left == 0 || right == 0) {
            return 1;
        }

        return 2;
    }
}
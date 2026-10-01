/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int cameras;
    public int minCameraCover(TreeNode root) {
       int state = dfs(root); 
        if(state == 2){
            cameras++;
          
        }
  return cameras;
    }
    public int dfs(TreeNode node){
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